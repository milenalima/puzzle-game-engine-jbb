package jbb.engine.pacman;

import java.util.HashMap;

import jbb.engine.GameView;
import jbb.engine.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

public class PacManView extends GameView {
	
	public PacManView(Context context) {
		super(context);
	}
	
	public PacManView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PacManView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void imageMap() {
		image_map = new HashMap<String,Bitmap>();
		Resources r = this.getContext().getResources();
		loadTile("blank", r.getDrawable(R.drawable.black_tile));
		loadTile("ghost_c", r.getDrawable(R.drawable.ghost_c));
		loadTile("ghost_d", r.getDrawable(R.drawable.ghost_d));
		loadTile("ghost_s", r.getDrawable(R.drawable.ghost_s));
		loadTile("pacdot", r.getDrawable(R.drawable.pacdot));
		loadTile("pacdot_powerpellet", r.getDrawable(R.drawable.pacdot_powerpellet));
		loadTile("pacman_down", r.getDrawable(R.drawable.pacman_down));
		loadTile("pacman_left", r.getDrawable(R.drawable.pacman_left));
		loadTile("pacman_up", r.getDrawable(R.drawable.pacman_up));
		loadTile("pacman_right", r.getDrawable(R.drawable.pacman_right));
		loadTile("pacman_invuln_down", r.getDrawable(R.drawable.pacman_invuln_down));
		loadTile("pacman_invuln_left", r.getDrawable(R.drawable.pacman_invuln_left));
		loadTile("pacman_invuln_up", r.getDrawable(R.drawable.pacman_invuln_up));
		loadTile("pacman_invuln_right", r.getDrawable(R.drawable.pacman_invuln_right));
		loadTile("wall", r.getDrawable(R.drawable.wall_brick));
	}
	
	public void onDraw(Canvas canvas) {
		mPaint.setColor(Color.BLACK);
		super.onDraw(canvas);
	}

}
