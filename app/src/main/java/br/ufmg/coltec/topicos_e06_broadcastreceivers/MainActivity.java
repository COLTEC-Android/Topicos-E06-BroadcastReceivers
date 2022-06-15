package br.ufmg.coltec.topicos_e06_broadcastreceivers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity{

    private static int CURRENT_THEME = R.style.Theme_TopicosE06BroadcastReceivers;

    private BroadcastReceiver batteryLowReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("BATTERY_RECEIVER", "Bateria fraca");

            switchActivityTheme(R.style.Theme_TopicosE06BroadcastReceiversLowBattery);
        }
    };

    private BroadcastReceiver batteryOkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("BATTERY_RECEIVER", "Bateria ok");

            switchActivityTheme(R.style.Theme_TopicosE06BroadcastReceivers);
        }
    };

    // TODO: Criar os receivers para tratar a mudança do tema

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(CURRENT_THEME);
        setContentView(R.layout.activity_main);

        IntentFilter filterLow = new IntentFilter(Intent.ACTION_BATTERY_LOW);

        IntentFilter filterOk = new IntentFilter(Intent.ACTION_BATTERY_OKAY);

        this.registerReceiver(this.batteryLowReceiver, filterLow);

        this.registerReceiver(this.batteryOkReceiver, filterOk);

        // TODO: registrar os receivers
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // TODO: desregistrar os receivers

        unregisterReceiver(this.batteryOkReceiver);
        unregisterReceiver(this.batteryLowReceiver);
    }

    /**
     * Realiza a troca do tema da Activity.
     *
     * Basicamente, esse método atualiza a variável estática responsável por definir o tema da
     * activity com o novo valor enviado por parâmetro, e recria toda a activity do zero para
     * que o novo tema seja aplicado.
     *
     * @param themeId Id do tema que será trocado.
     *                O parâmetro se refere ao ID que o tema possui na classe R.java.
     *                Para esse app específico, podemos informar dois possíveis IDs.
     *                - R.style.Theme_TopicosE06BroadcastReceivers (Tema padrão)
     *                - R.style.Theme_TopicosE06BroadcastReceiversLowBattery (bateria baixa)
     */
    private void switchActivityTheme(int themeId) {
        CURRENT_THEME = themeId;
        MainActivity.this.finish();
        MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity.class));
    }
}