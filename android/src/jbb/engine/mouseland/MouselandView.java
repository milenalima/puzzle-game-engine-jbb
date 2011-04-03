package jbb.engine.mouseland;

import java.util.HashMap;

import jbb.engine.GameView;
import jbb.engine.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.AttributeSet;

public class MouselandView extends GameView {
	
	public MouselandView(Context context) {
		super(context);
	}
	
	public MouselandView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MouselandView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void imageMap() {
		image_map = new HashMap<String,Bitmap>();
		Resources r = this.getContext().getResources();
		loadTile("blank", r.getDrawable(R.drawable.white_tile));
		loadTile("cheesy_down", r.getDrawable(R.drawable.cheesy_down));
		loadTile("cheesy_left", r.getDrawable(R.drawable.cheesy_left));
		loadTile("cheesy_right", r.getDrawable(R.drawable.cheesy_right));
		loadTile("cheesy_up", r.getDrawable(R.drawable.cheesy_up));
		loadTile("mouse_trap", r.getDrawable(R.drawable.mouse_trap));
		loadTile("mouse_trap_deactivated", r.getDrawable(R.drawable.mouse_trap_deactivated));
		loadTile("red_eyes_down", r.getDrawable(R.drawable.red_eyes_down));
		loadTile("red_eyes_left", r.getDrawable(R.drawable.red_eyes_left));
		loadTile("red_eyes_right", r.getDrawable(R.drawable.red_eyes_right));
		loadTile("red_eyes_up", r.getDrawable(R.drawable.red_eyes_up));
		loadTile("wall", r.getDrawable(R.drawable.wall_brick));
	}

}
