package com.taopao.tpdialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/**
 * @Author: TaoPao
 * @Date: 2/20/21 9:00 AM
 * @Description: java类作用描述
 */
public class TpBottomSheet extends BaseTpDialog {
    private final TpBottomSheetRootLayout mRootView;
    private final TpBottomSheetBehavior<TpBottomSheetRootLayout> mBehavior;
    private boolean mAnimateToCancel = false;
    private boolean mAnimateToDismiss = false;
    private OnBottomSheetShowListener mOnBottomSheetShowListener;
    private View mTouchOutside;
    private ShadowBgAnimator mShadowBgAnimator;

    public void setDialogInfo(DialogInfo dialogInfo) {
        mDialogInfo = dialogInfo;
    }

    private DialogInfo mDialogInfo = new DialogInfo();

    public TpBottomSheet(Context context) {
        super(context, R.style.Tp_BottomSheet);

        ViewGroup container = (ViewGroup) getLayoutInflater().inflate(R.layout.tp_bottom_sheet_dialog, null);
        mRootView = container.findViewById(R.id.bottom_sheet);
        mTouchOutside = container.findViewById(R.id.touch_outside);
        mShadowBgAnimator = new ShadowBgAnimator(container);
        mShadowBgAnimator.initAnimator();
        mBehavior = new TpBottomSheetBehavior<>();
        mBehavior.setHideable(cancelable);
        mBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.e("===", "onStateChanged: " + newState);
                if (newState == BottomSheetBehavior.STATE_HIDDEN || newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    if (mAnimateToCancel) {
                        // cancel() invoked
                        cancel();
                    } else if (mAnimateToDismiss) {
                        // dismiss() invoked but it it not triggered by cancel()
                        dismiss();
                    } else {
                        // drag to cancel
                        cancel();
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (mTouchOutside != null)
                    container.setBackgroundColor(mShadowBgAnimator.calculateBgColor(slideOffset));

            }
        });
        mBehavior.setPeekHeight(0);
        mBehavior.setAllowDrag(true);
        mBehavior.setSkipCollapsed(true);
        CoordinatorLayout.LayoutParams rootViewLp = (CoordinatorLayout.LayoutParams) mRootView.getLayoutParams();
        rootViewLp.setBehavior(mBehavior);
        mTouchOutside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBehavior.getState() == BottomSheetBehavior.STATE_SETTLING) {
                    return;
                }
                if (cancelable && isShowing() && shouldWindowCloseOnTouchOutside()) {
                    cancel();
                }
            }
        });
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                // Consume the event and prevent it from falling through
                return true;
            }
        });

        super.setContentView(container);
    }


    protected View onCreateContentView(@NonNull Context context, @NonNull final TpBottomSheet bottomSheet,
                                       DialogInfo dialogInfo) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        QMUIBottomSheetListAdapter adapter = new QMUIBottomSheetListAdapter(
                dialogInfo.needRightMark, dialogInfo.gravityCenter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });
        recyclerView.addItemDecoration(new QMUIBottomSheetListItemDecoration(context));

        LinearLayout headerView = null;
        if (dialogInfo.contentHeaderViews != null && dialogInfo.contentHeaderViews.size() > 0) {
            headerView = new LinearLayout(context);
            headerView.setOrientation(LinearLayout.VERTICAL);
            for (View view : dialogInfo.contentHeaderViews) {
                if (view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
                headerView.addView(view, new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }
        LinearLayout footerView = null;
        if (dialogInfo.contentFooterViews != null && dialogInfo.contentFooterViews.size() > 0) {
            footerView = new LinearLayout(context);
            footerView.setOrientation(LinearLayout.VERTICAL);
            for (View view : dialogInfo.contentFooterViews) {
                if (view.getParent() != null) {
                    ((ViewGroup) view.getParent()).removeView(view);
                }
                footerView.addView(view, new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        }
        adapter.setData(headerView, footerView, dialogInfo.mItems);
        adapter.setOnItemClickListener(new QMUIBottomSheetListAdapter.OnItemClickListener() {
            @Override
            public void onClick(QMUIBottomSheetListAdapter.VH vh, int dataPos, TpBottomSheetListItemModel model) {
                if (dialogInfo.onSheetItemClickListener != null) {
                    dialogInfo.onSheetItemClickListener.onClick(bottomSheet, vh.itemView, dataPos, model.tag);
                }
            }
        });
        adapter.setCheckedIndex(dialogInfo.checkedIndex);
        recyclerView.scrollToPosition(dialogInfo.checkedIndex + (headerView == null ? 0 : 1));
        return recyclerView;
    }


    @Override
    protected void onSetCancelable(boolean cancelable) {
        super.onSetCancelable(cancelable);
        mBehavior.setHideable(cancelable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewCompat.requestApplyInsets(mRootView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        mShadowBgAnimator.animateShow();
    }

    @Override
    public void cancel() {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            mAnimateToCancel = false;
            super.cancel();
        } else {
            mAnimateToCancel = true;
            mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    @Override
    public void dismiss() {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
            mAnimateToDismiss = false;
            super.dismiss();
        } else {
            mAnimateToDismiss = true;
            mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
        mShadowBgAnimator.animateDismiss();
    }

    public void setOnBottomSheetShowListener(OnBottomSheetShowListener onBottomSheetShowListener) {
        mOnBottomSheetShowListener = onBottomSheetShowListener;
    }


    public LinearLayout getRootView() {
        return mRootView;
    }

    public TpBottomSheetBehavior<TpBottomSheetRootLayout> getBehavior() {
        return mBehavior;
    }

    @Override
    public void show() {
        super.show();
        if (mOnBottomSheetShowListener != null) {
            mOnBottomSheetShowListener.onShow();
        }
        if (mBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            mRootView.postOnAnimation(new Runnable() {
                @Override
                public void run() {
                    mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            });
        }
        mAnimateToCancel = false;
        mAnimateToDismiss = false;
    }

    public interface OnBottomSheetShowListener {
        void onShow();
    }

    @Override
    public void setContentView(View view) {
        throw new IllegalStateException(
                "Use addContentView(View, ConstraintLayout.LayoutParams) for replacement");
    }

    @Override
    public void setContentView(int layoutResId) {
        throw new IllegalStateException(
                "Use addContentView(int) for replacement");
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        throw new IllegalStateException(
                "Use addContentView(View, QMUIPriorityLinearLayout.LayoutParams) for replacement");
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        throw new IllegalStateException(
                "Use addContentView(View, QMUIPriorityLinearLayout.LayoutParams) for replacement");
    }

    public void addContentView(View view, LinearLayout.LayoutParams layoutParams) {
        mRootView.addView(view, layoutParams);
    }

    public void addContentView(View view) {
        TpBottomSheetRootLayout.LayoutParams lp = new TpBottomSheetRootLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setPriority(TpBottomSheetRootLayout.LayoutParams.PRIORITY_DISPOSABLE);
        mRootView.addView(view, lp);
    }

    public void addContentView(int layoutResId) {
        LayoutInflater.from(mRootView.getContext()).inflate(layoutResId, mRootView, true);
    }
}