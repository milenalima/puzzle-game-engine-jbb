package jbb.engine;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public abstract class GameView extends View implements Observer {
	
	private int height_of_image = 20;
	private int width_of_image = 20;
	private int left = 0;
	private int top = 0;
	
	protected HashMap<String,Bitmap> image_map;
	private String[][] m_board;
	private Board board;
	
	private RefreshHandler mRedrawHandler = new RefreshHandler();
	protected final Paint mPaint = new Paint();
	
	public GameView(Context context) {
		super(context);
		imageMap();
	}
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		imageMap();
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		imageMap();
	}
	
	public abstract void imageMap();

	public void loadTile(String string, Drawable drawable) {
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
		if (data.equals("update")) {
			mRedrawHandler.sendEmptyMessage(0);
		} else {
			AlertDialog.Builder myDiag = new AlertDialog.Builder(this.getContext());
			DiagHandler dh = new DiagHandler();
			myDiag.setPositiveButton("Play Again", dh);
			myDiag.setNegativeButton("Quit Game", dh);
			Resources r = this.getContext().getResources();
			myDiag.setTitle((String)data);
			myDiag.setIcon(r.getDrawable(R.drawable.esfandiari));
			myDiag.setCancelable(false);
			myDiag.show();
		}
	}
	
	class DiagHandler implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			if (which == DialogInterface.BUTTON_POSITIVE) {
				board.restartGame();
			} else if (which == DialogInterface.BUTTON_NEGATIVE) {
				((Activity)GameView.this.getContext()).finish();
			}
		}
		
	}
	
	class RefreshHandler extends Handler {
		
		@Override
		public void handleMessage(Message msg) {
			GameView.this.invalidate();
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
		canvas.drawPaint(mPaint);
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
		height_of_image = (int) Math.floor((h) / board.getHeight());
		top = ((h - (height_of_image * board.getHeight())) / 2);
		left = ((w - (width_of_image * board.getWidth())) / 2);
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
					// do nothing to not interrupt user
				}
			}
			return true;
		}
		return true;
	}

}
