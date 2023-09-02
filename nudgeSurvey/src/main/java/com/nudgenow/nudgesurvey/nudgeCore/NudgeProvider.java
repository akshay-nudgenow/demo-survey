package com.nudgenow.nudgesurvey.nudgeCore;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nudgenow.nudgesurvey.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import java.util.Map;

public class NudgeProvider extends AppCompatActivity {
    private FrameLayout container;
    public static Nudge nudge;
    private List<NudgeUi> plugins;
    private NudgeCallback log;

    NudgeProvider(Nudge nudge, List<NudgeUi> plugins) {
        NudgeProvider.nudge = nudge;
        this.plugins = plugins;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nudge_provider);

        container = findViewById(R.id.container);

        nudge = new Nudge("API Key");
        plugins = new ArrayList<>();

        /*
        plugins.add(new NudgeUi1());
        plugins.add(new NudgeUi2());
        */

        /*log = new NudgeCallback() {
            @Override
            public void onLog(String logMessage) {

            }
        };

         */

        nudge.setTrackCall(new Nudge.CallbackInterface() {
            @Override
            public void onTrackDataReceived(Object trackData) {
                trackcalled((Map<String, Object>) trackData);
            }
        });
    }

    private void trackcalled(Map<String, Object> trackdata) {
        Map<String, NudgeUi> gameKeyUiMapper = new HashMap<>();

        for (NudgeUi plugin : plugins) {
            gameKeyUiMapper.put(plugin.getType(), plugin);
        }

        for (Map.Entry<String, Object> entry : trackdata.entrySet()) {
            String gameKey = entry.getKey();
            Map<String, Object> campaign = (Map<String, Object>) entry.getValue();

            NudgeUi nudgeUi = gameKeyUiMapper.get(gameKey);
            if (nudgeUi != null) {
                try {
                    nudgeUi.copyWith((String) campaign.get("gameSettingsId"), nudge.getToken())
                            .trigger(this, (String) campaign.get("userStatId"), (String) campaign.get("position"));
                } catch (Exception e) {
                    if (log != null) {
                        log.onLog(e.toString());
                    }
                    e.printStackTrace();
                }
            } else {
                String e = "No plugin found for " + gameKey + ", ignoring.";
                if (log != null) {
                    log.onLog(e);
                }
            }
        }
    }

    public interface NudgeCallback {
        void onLog(String logMessage);
    }
}
