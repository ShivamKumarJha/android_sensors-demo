package shivam.sensorsdemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView l ;
    SensorManager sm;
    SensorEventListener sel;
    ArrayList name,vendor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
}
