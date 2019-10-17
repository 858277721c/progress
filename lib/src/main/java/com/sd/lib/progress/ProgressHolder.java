package com.sd.lib.progress;

public abstract class ProgressHolder implements ProgressView
{
    private int mProgress = 0;
    // 暂时不对外开放修改
    private final int mMin = 0;
    private int mMax = 100;

    @Override
    public int getProgress()
    {
        return mProgress;
    }

    @Override
    public int getMax()
    {
        return mMax;
    }

    @Override
    public float getProgressPercent()
    {
        if (mMax < mMin)
            throw new RuntimeException("max < min max:" + mMax + " min:" + mMin);

        if (mMax == mMin)
            return 0.0f;

        final int total = mMax - mMin;
        return (float) mProgress / total;
    }

    @Override
    public boolean setProgress(int progress)
    {
        final int limitMin = mMin;
        final int limitMax = mMax;

        if (progress < limitMin)
            progress = limitMin;

        if (progress > limitMax)
            progress = limitMax;

        if (mProgress != progress)
        {
            mProgress = progress;
            return true;
        }
        return false;
    }

    @Override
    public boolean setMax(int max)
    {
        if (max < mMin)
            max = mMin;

        if (mMax != max)
        {
            mMax = max;

            if (setProgress(mProgress))
                onProgressFixIntoRange();
            return true;
        }
        return false;
    }

    protected abstract void onProgressFixIntoRange();
}
