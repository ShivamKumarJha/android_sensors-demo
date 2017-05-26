package shivam.sensorsdemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView l ;
    SensorManager sm;
    SensorEventListener sel;
    ArrayList name,vendor;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = (TextView) findViewById(R.id.t1);
        name = new ArrayList();
        vendor = new ArrayList();
        l = (ListView) findViewById(R.id.slist);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> ls = sm.getSensorList(Sensor.TYPE_ALL);

        for (int i=0; i<ls.size();i++)
        {
            name.add(ls.get(i).getName());
            vendor.add(ls.get(i).getVendor());
        }

        ArrayAdapter a = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,name);
        l.setAdapter(a);
        Toast.makeText(this, ""+ls.size(), Toast.LENGTH_SHORT).show();
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, ""+vendor.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        Sensor s = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                /*t.setText("X="+event.values[0]+
                        "\nY="+event.values[1]+
                        "\nZ="+event.values[2]);*/
                if (event.values[0]==0)
                {
                    t.setText("DONT TOUCH ME");
                }
                else
                {
                    t.setText("TOUCH ME");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sm.registerListener(sel,s,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
