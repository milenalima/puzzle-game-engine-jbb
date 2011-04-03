package jbb.engine.pipes;

import java.util.HashMap;

import jbb.engine.GameView;
import jbb.engine.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

public class PipesView extends GameView {
	
	public PipesView(Context context) {
		super(context);
	}
	
	public PipesView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PipesView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void imageMap() {
		image_map = new HashMap<String,Bitmap>();
		Resources r = this.getContext().getResources();
		loadTile("blank", r.getDrawable(R.drawable.black_tile));
		loadTile("pipe_i_1", r.getDrawable(R.drawable.pipe_i_1));
		loadTile("pipe_i_2", r.getDrawable(R.drawable.pipe_i_2));
		loadTile("pipe_i_3", r.getDrawable(R.drawable.pipe_i_3));
		loadTile("pipe_i_4", r.getDrawable(R.drawable.pipe_i_4));
		loadTile("pipe_i_water_1", r.getDrawable(R.drawable.pipe_i_water_1));
		loadTile("pipe_i_water_2", r.getDrawable(R.drawable.pipe_i_water_2));
		loadTile("pipe_i_water_3", r.getDrawable(R.drawable.pipe_i_water_3));
		loadTile("pipe_i_water_4", r.getDrawable(R.drawable.pipe_i_water_4));
		loadTile("pipe_l_1", r.getDrawable(R.drawable.pipe_l_1));
		loadTile("pipe_l_2", r.getDrawable(R.drawable.pipe_l_2));
		loadTile("pipe_l_3", r.getDrawable(R.drawable.pipe_l_3));
		loadTile("pipe_l_4", r.getDrawable(R.drawable.pipe_l_4));
		loadTile("pipe_l_water_1", r.getDrawable(R.drawable.pipe_l_water_1));
		loadTile("pipe_l_water_2", r.getDrawable(R.drawable.pipe_l_water_2));
		loadTile("pipe_l_water_3", r.getDrawable(R.drawable.pipe_l_water_3));
		loadTile("pipe_l_water_4", r.getDrawable(R.drawable.pipe_l_water_4));
		loadTile("pipe_plus_1", r.getDrawable(R.drawable.pipe_plus_1));
		loadTile("pipe_plus_2", r.getDrawable(R.drawable.pipe_plus_2));
		loadTile("pipe_plus_3", r.getDrawable(R.drawable.pipe_plus_3));
		loadTile("pipe_plus_4", r.getDrawable(R.drawable.pipe_plus_4));
		loadTile("pipe_plus_water_1", r.getDrawable(R.drawable.pipe_plus_water_1));
		loadTile("pipe_plus_water_2", r.getDrawable(R.drawable.pipe_plus_water_2));
		loadTile("pipe_plus_water_3", r.getDrawable(R.drawable.pipe_plus_water_3));
		loadTile("pipe_plus_water_4", r.getDrawable(R.drawable.pipe_plus_water_4));
		loadTile("pipe_q_1", r.getDrawable(R.drawable.pipe_q_1));
		loadTile("pipe_q_2", r.getDrawable(R.drawable.pipe_q_2));
		loadTile("pipe_q_3", r.getDrawable(R.drawable.pipe_q_3));
		loadTile("pipe_q_4", r.getDrawable(R.drawable.pipe_q_4));
		loadTile("pipe_q_water_1", r.getDrawable(R.drawable.pipe_q_water_1));
		loadTile("pipe_q_water_2", r.getDrawable(R.drawable.pipe_q_water_2));
		loadTile("pipe_q_water_3", r.getDrawable(R.drawable.pipe_q_water_3));
		loadTile("pipe_q_water_4", r.getDrawable(R.drawable.pipe_q_water_4));
		loadTile("pipe_t_1", r.getDrawable(R.drawable.pipe_t_1));
		loadTile("pipe_t_2", r.getDrawable(R.drawable.pipe_t_2));
		loadTile("pipe_t_3", r.getDrawable(R.drawable.pipe_t_3));
		loadTile("pipe_t_4", r.getDrawable(R.drawable.pipe_t_4));
		loadTile("pipe_t_water_1", r.getDrawable(R.drawable.pipe_t_water_1));
		loadTile("pipe_t_water_2", r.getDrawable(R.drawable.pipe_t_water_2));
		loadTile("pipe_t_water_3", r.getDrawable(R.drawable.pipe_t_water_3));
		loadTile("pipe_t_water_4", r.getDrawable(R.drawable.pipe_t_water_4));
		loadTile("w", r.getDrawable(R.drawable.ghost_s));
		loadTile("p", r.getDrawable(R.drawable.ghost_s));
		loadTile("wall", r.getDrawable(R.drawable.wall_brick));
	}
	
	public void onDraw(Canvas canvas) {
		mPaint.setColor(Color.BLACK);
		super.onDraw(canvas);
	}

}
