package jbb.engine.mouseland;

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

public class MouselandActivity extends Activity {
	
	private TextView lives;
	private Button traps;
	
	MouseLand board;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mouseland);
        InputStream[] is = new InputStream[3];
        System.out.println("here");
        is[0] = getResources().openRawResource(R.raw.mouseland_map1);
        System.out.println("there");
        is[1] = getResources().openRawResource(R.raw.mouseland_map2);
        is[2] = getResources().openRawResource(R.raw.mouseland_map3);
        board = new MouseLand(is);
        MouselandView view = (MouselandView) findViewById(R.id.mouseland_view);
        view.setModel(board);
        lives = (TextView) findViewById(R.id.lives);
        traps = (Button) findViewById(R.id.traps);
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
    
    public void setLives(String text) {
    	lives.setText(text);
    }
    
    public void setTraps(String text) {
    	traps.setText(text);
    }
	
	public void onClickTrap(View v) {
		MouseHero hero = (MouseHero)board.getHero();
		hero.setTrap();
		traps.setText("Set trap (" + hero.getNumMouseTraps() + ")");
	}
}