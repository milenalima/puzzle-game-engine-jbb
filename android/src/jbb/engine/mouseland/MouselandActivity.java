package jbb.engine.mouseland;

import jbb.engine.R;
import android.app.Activity;
import android.os.Bundle;

public class MouselandActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mouseland);
        MouseLand board = new MouseLand();
        MouselandView view = (MouselandView) findViewById(R.id.mouseland_view);
        view.setModel(board);
   }
}