package br.ufmg.coltec.topicos_e06_broadcastreceivers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static int CURRENT_THEME = R.style.Theme_TopicosE06BroadcastReceivers;

    // TODO: Criar os receivers para tratar a mudança do tema
    BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().toString().equals("android.intent.action.BATTERY_LOW")){
                switchActivityTheme(R.style.Theme_TopicosE06BroadcastReceiversLowBattery);
            }
            else{
                switchActivityTheme(R.style.Theme_TopicosE06BroadcastReceivers);
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        // TODO: registrar os receivers
        IntentFilter batteryFilterLow = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        IntentFilter batteryFilterOkay = new IntentFilter(Intent.ACTION_BATTERY_OKAY);

        this.registerReceiver(batteryReceiver, batteryFilterLow);
        this.registerReceiver(batteryReceiver, batteryFilterOkay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(CURRENT_THEME);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // TODO: desregistrar os receivers
        this.unregisterReceiver(batteryReceiver);
    }

    // on segundo plano e registrar de novo

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