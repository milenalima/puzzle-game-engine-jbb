package jbb.engine.pipes;

import java.io.InputStream;

import jbb.engine.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PipesActivity extends Activity {
	
	TextView nextPipe;
	TextView waterDelay;
	Button runWater;
	
	PipeMap board;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pipes);
        InputStream[] is = new InputStream[3];
        is[0] = getResources().openRawResource(R.raw.pipe_map1);
        is[1] =  getResources().openRawResource(R.raw.pipe_map2);
        is[2] =  getResources().openRawResource(R.raw.pipe_map3);
        board = new PipeMap(is);
        PipesView view = (PipesView) findViewById(R.id.pipes_view);
        view.setModel(board);
        nextPipe = (TextView) findViewById(R.id.nextPipe);
        waterDelay = (TextView) findViewById(R.id.waterDelay);
        runWater = (Button) findViewById(R.id.runWaterButton);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.undo_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.undo_item:
            System.out.println("undo");
            return true;
        case R.id.redo_item:
        	System.out.println("redo");
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    public void setNextPipe(String text) {
    	nextPipe.setText(text);
    }
    
    public void setWaterDelay(String text) {
    	waterDelay.setText(text);
    }
    
    public void setRunWater(String text) {
    	runWater.setText(text);
    }
    
    public void onClickWater(View v) {
    	board.runWaterPressed();
    	setRunWater("Water is running!");
    }
}