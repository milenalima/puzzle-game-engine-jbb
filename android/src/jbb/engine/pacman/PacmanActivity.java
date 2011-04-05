package jbb.engine.pacman;

import java.io.InputStream;

import jbb.engine.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class PacmanActivity extends Activity {
	
	private TextView lives;
	private TextView points;
	private PacWorld board;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pacman);
        InputStream[] is = new InputStream[3];
        is[0] = getResources().openRawResource(R.raw.pacman_map1);
        is[1] = getResources().openRawResource(R.raw.pacman_map2);
        is[2] = getResources().openRawResource(R.raw.pacman_map3);
        board = new PacWorld(is);
        PacManView view = (PacManView) findViewById(R.id.pacman_view);
        view.setModel(board);
        lives = (TextView) findViewById(R.id.lives);
        points = (TextView) findViewById(R.id.points);
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
            board.undoMove();
            return true;
        case R.id.redo_item:
        	board.redoMove();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    public void setLives(String text) {
    	lives.setText(text);
    }
    
    public void setPoints(String text) {
    	points.setText(text);
    }
}