package rja.android.gl;

import javax.microedition.khronos.opengles.GL10;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class GLFigure {

    private IntBuffer   mVertexBuffer;
    private IntBuffer   mColorBuffer;
    private ByteBuffer  mIndexBuffer;

	private int pointsToDraw = 6;

	private boolean cullBackSurface = false;

	public GLFigure() {
        int one = 0x10000;
        int vertices[] = {
                -one, -one, -one,
                one, -one, -one,
                one,  one, -one,
                -one,  one, -one,
                -one, -one,  one,
                one, -one,  one,
                one,  one,  one,
                -one,  one,  one,
        };

        int colors[] = {
                0,    0,    0,  one,
                one,    0,    0,  one,
                one,  one,    0,  one,
                0,  one,    0,  one,
                0,    0,  one,  one,
                one,    0,  one,  one,
                one,  one,  one,  one,
                0,  one,  one,  one,
        };

        byte indices[] = {
                0, 4, 5,    0, 5, 1,
                1, 5, 6,    1, 6, 2,
                2, 6, 7,    2, 7, 3,
                3, 7, 4,    3, 4, 0,
                4, 7, 6,    4, 6, 5,
                3, 0, 1,    3, 1, 2
        };

        // Buffers to be passed to gl*Pointer() functions
        // must be direct, i.e., they must be placed on the
        // native heap where the garbage collector cannot
        // move them.
        //
        // Buffers with multi-byte datatypes (e.g., short, int, float)
        // must have their byte order set to native order

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asIntBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
        cbb.order(ByteOrder.nativeOrder());
        mColorBuffer = cbb.asIntBuffer();
        mColorBuffer.put(colors);
        mColorBuffer.position(0);

        mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
        mIndexBuffer.put(indices);
        mIndexBuffer.position(0);
    }

    public void draw(GL10 gl) {
        gl.glFrontFace(gl.GL_CW);

		if (cullBackSurface) {
			gl.glEnable(gl.GL_CULL_FACE);
		} else {
			gl.glDisable(gl.GL_CULL_FACE);
		}

        gl.glVertexPointer(3, gl.GL_FIXED, 0, mVertexBuffer);
        gl.glColorPointer(4, gl.GL_FIXED, 0, mColorBuffer);
        gl.glDrawElements(gl.GL_TRIANGLES, pointsToDraw, gl.GL_UNSIGNED_BYTE, mIndexBuffer);
    }

	public int getPointsToDraw() {
		return pointsToDraw;
	}

	public void setPointsToDraw(int pointsToDraw) {
		this.pointsToDraw = pointsToDraw;
	}

	public void incrementTriangles() {
		if (pointsToDraw <= 33) {
			pointsToDraw += 3;
		}

		if (pointsToDraw >= 36) {
			cullBackSurface = true;
		}
	}

	public void decrementTriangles() {

		if (pointsToDraw >= 3) {
			pointsToDraw -= 3;
		}

		if (pointsToDraw < 36) {
			cullBackSurface = false;
		}
	}
}
