package rja.android.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class GLView extends GLSurfaceView {

	private GLRenderer renderer;
    private float previousX;
    private float previousY;

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private final float TRACKBALL_SCALE_FACTOR = 36.0f;

	public GLView(Context context) {
		super(context);
		loadRenderer();
	}

	public GLView(Context context, AttributeSet  attrs) {
		super(context, attrs);
		loadRenderer();
	}

	private void loadRenderer() {
		renderer = new GLRenderer();
		setRenderer(renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

    @Override public boolean onTrackballEvent(MotionEvent e) {
        renderer.angleX += e.getX() * TRACKBALL_SCALE_FACTOR;
        renderer.angleY += e.getY() * TRACKBALL_SCALE_FACTOR;
        requestRender();
        return true;
    }

    @Override public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();
        switch (e.getAction()) {
        case MotionEvent.ACTION_MOVE:
            float dx = x - previousX;
            float dy = y - previousY;
            renderer.angleX += dx * TOUCH_SCALE_FACTOR;
            renderer.angleY += dy * TOUCH_SCALE_FACTOR;
            requestRender();
        }
        previousX = x;
        previousY = y;
        return true;
    }

	public GLRenderer getRenderer() {
		return renderer;
	}
}
