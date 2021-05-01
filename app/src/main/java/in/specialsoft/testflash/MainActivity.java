package in.specialsoft.testflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_flash;
    CameraManager cameraManager;
    int flash = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_flash = findViewById(R.id.btn_flash);

        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

    }

    public void startStopFlash(View view) {
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
                //Turn flash on FLASH
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (flash==1){
                            cameraManager.setTorchMode("0",false);
                            flash=0;
                            btn_flash.setBackgroundResource(R.drawable.ic_flash);
                            Toast.makeText(this, "Flash OFF !", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            cameraManager.setTorchMode("0",true);
                            flash=1;
                            btn_flash.setBackgroundResource(R.drawable.ic_off);
                            Toast.makeText(this, "Flash ON !", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }

            }
            else{
                Toast.makeText(this, "This device has no flash", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "No camera available", Toast.LENGTH_SHORT).show();
        }
    }
}