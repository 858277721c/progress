package com.sd.lib.progress;

public abstract class ProgressHolder implements ProgressView
{
    private int mMinProgress = 0;
    private int mMaxProgress = 100;
    private int mProgress = 0;

    @Override
    public int getProgress()
    {
        return mProgress;
    }

    @Override
    public int getMinProgress()
    {
        return mMinProgress;
    }

    @Override
    public int getMaxProgress()
    {
        return mMaxProgress;
    }

    @Override
    public float getProgressPercent()
    {
        if (mMaxProgress <= 0)
            return 0.0f;

        return (float) mProgress / mMaxProgress;
    }

    @Override
    public boolean setProgress(int progress)
    {
        if (progress < mMinProgress)
            progress = mMinProgress;

        if (progress > mMaxProgress)
            progress = mMaxProgress;

        if (mProgress != progress)
        {
            mProgress = progress;
            return true;
        }
        return false;
    }

    @Override
    public void setMinProgress(int progress)
    {
        if (progress < 0)
            progress = 0;

        if (mMinProgress != progress)
        {
            mMinProgress = progress;
            checkProgressBound();

            if (setProgress(mProgress))
                onProgressFixIntoRange();
        }
    }

    private void checkProgressBound()
    {
        if (mMinProgress > mMaxProgress)
            throw new RuntimeException("mMinProgress > mMaxProgress min:" + mMinProgress + " max:" + mMaxProgress);
    }

    @Override
    public void setMaxProgress(int progress)
    {
        if (mMaxProgress != progress)
        {
            mMaxProgress = progress;
            checkProgressBound();

            if (setProgress(mProgress))
                onProgressFixIntoRange();
        }
    }

    protected abstract void onProgressFixIntoRange();
}
