package rja.android.gl;

import javax.microedition.khronos.egl.EGLConfig;
import android.opengl.GLSurfaceView;
import javax.microedition.khronos.opengles.GL10;

/**
 * Describe class GLRenderer here.
 *
 *
 * Created: Mon Dec 27 18:39:15 2010
 *
 * @author <a href="mailto:rajeshja@D-174758"></a>
 * @version 1.0
 */
public class GLRenderer implements GLSurfaceView.Renderer {

	private GLFigure figure;

	public float angleX;
	public float angleY;

	public GLRenderer() {
		figure = new GLFigure();
	}

	public void onDrawFrame(GL10 gl) {
		/*
		 * Usually, the first thing one might want to do is to clear
		 * the screen. The most efficient way of doing this is to use
		 * glClear().
		 */
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		/*
		 * Now we're ready to draw some 3D objects
		 */
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -3.0f);
		gl.glRotatef(angleX, 0, 1, 0);
		gl.glRotatef(angleY, 1, 0, 0);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		//gl.glColor4f(1.0, 1.0, 1.0, 1.0);
		//gl.glOrthof(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
		
		figure.draw(gl);
	}
	
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		
		/*
		 * Set our projection matrix. This doesn't have to be done
		 * each time we draw, but usually a new projection needs to
		 * be set when the viewport is resized.
		 */
		
		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		/*
		 * By default, OpenGL enables features that improve quality
		 * but reduce performance. One might want to tweak that
		 * especially on software renderer.
		 */
		gl.glDisable(GL10.GL_DITHER);
		
		/*
		 * Some one-time OpenGL initialization can be made here
		 * probably based on features of this particular context
		 */
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
				  GL10.GL_FASTEST);
		
		
		gl.glClearColor(1,1,1,1);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glEnable(GL10.GL_DEPTH_TEST);
	}

	public GLFigure getFigure() {
		return figure;
	}
}
