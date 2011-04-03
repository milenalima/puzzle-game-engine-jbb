package jbb.engine;

import jbb.engine.mouseland.MouselandActivity;
import jbb.engine.pacman.PacmanActivity;
import jbb.engine.pipes.PipesActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameEngineActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameselect);
   }
    
    public void onClickPacman(View v) {
    	Intent intent = new Intent(GameEngineActivity.this,PacmanActivity.class);
    	GameEngineActivity.this.startActivity(intent);
    }
    
    public void onClickMouseland(View v) {
    	Intent intent = new Intent(GameEngineActivity.this,MouselandActivity.class);
    	GameEngineActivity.this.startActivity(intent);
    }

    public void onClickPipes(View v) {
    	Intent intent = new Intent(GameEngineActivity.this,PipesActivity.class);
    	GameEngineActivity.this.startActivity(intent);
    }
}