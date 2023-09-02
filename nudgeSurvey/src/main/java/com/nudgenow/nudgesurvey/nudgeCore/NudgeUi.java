package com.nudgenow.nudgesurvey.nudgeCore;

import android.content.Context;



import android.content.Context;


import android.content.Context;

public abstract class NudgeUi {
    public abstract String getId();
    public abstract String getToken();
    public abstract String getType();

    public NudgeUi() {
    }

    public abstract NudgeUi copyWith(String id, String token);

    public abstract void trigger(Context context, String userStatId, String position);
    public abstract void getCallback(NudgeProvider.NudgeCallback callback);

    public abstract void trigger(String userStatId, String position);
}
