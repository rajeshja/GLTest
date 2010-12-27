package rja.android.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GLView extends GLSurfaceView {

	GLRenderer renderer;

	/**
	 * Creates a new <code>GLView</code> instance.
	 *
	 */
	public GLView(Context context) {
		super(context);
		renderer = new GLRenderer();
		setRenderer(renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

}
