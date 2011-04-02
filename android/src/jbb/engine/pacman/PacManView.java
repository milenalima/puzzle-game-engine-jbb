package jbb.engine.pacman;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import jbb.engine.Board;
import jbb.engine.Position;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PacManView extends View implements Observer {
	
	private int height_of_image = 13;
	private int width_of_image = 13;
	private int left = 0;
	private int top = 0;
	
	private HashMap<String,Bitmap> image_map;
	private String[][] m_board;
	private Board board;
	
	private RefreshHandler mRedrawHandler = new RefreshHandler();
	private final Paint mPaint = new Paint();
	
	public PacManView(Context context) {
		super(context);
		imageMap();
	}
	
	public PacManView(Context context, AttributeSet attrs) {
		super(context, attrs);
		imageMap();
	}

	public PacManView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		imageMap();
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

	private void loadTile(String string, Drawable drawable) {
		BitmapDrawable bd = (BitmapDrawable)drawable;
		Bitmap bitmap = bd.getBitmap();
		image_map.put(string, bitmap);
	}

	public void setModel(Board board) {
		this.board = board;
		board.addObserver(this);
		refreshView();
	}

	public void refreshView() {
		m_board = new String[board.getHeight()][board.getWidth()];
		for (int row = 0; row < board.getHeight(); row++) {
			for (int col = 0; col < board.getWidth(); col++) {
				m_board[row][col] = board.getTile(new Position(row,col)).toString();
			}	
		}
	}

	@Override
	public void update(Observable observable, Object data) {
		refreshView();
		mRedrawHandler.sendEmptyMessage(0);
	}
	
	class RefreshHandler extends Handler {
		
		@Override
		public void handleMessage(Message msg) {
			PacManView.this.invalidate();
		}
		
		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	};
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setColor(Color.BLACK);
		canvas.drawPaint(mPaint);
		left = canvas.getWidth()/2 - board.getWidth()*width_of_image/2;
		top = canvas.getHeight()/2 - board.getHeight()*height_of_image/2;
		for (int row = 0; row < board.getHeight(); row++) {
			for (int col = 0; col < board.getWidth(); col ++) {
				canvas.drawBitmap(image_map.get(board.getTile(new Position(row,col)).toString()),
						left + col * width_of_image,
						top + row * height_of_image,
						mPaint);
			}
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width_of_image = (int) Math.ceil(w / board.getWidth());
		height_of_image = (int) Math.floor(h / board.getHeight());
		left = ((w - (width_of_image * board.getWidth())) / 2);
		top = ((h - (height_of_image * board.getHeight())) / 2);
		for(String key : image_map.keySet()){
			image_map.put(key,Bitmap.createScaledBitmap(image_map.get(key),width_of_image,
						height_of_image, false));
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			return true;
		} else if (action == MotionEvent.ACTION_UP) {
			int x = (int) event.getX();
			int y = (int) event.getY();
			int col = (x-left)/width_of_image;
			int row = (y-top)/height_of_image;
			if(this.board != null){
				try {
					this.board.playTurn(new Position(row,col));
				} catch (Exception e) {
					// do nothing
				}
			}
			return true;
		}
		return true;
	}

}
