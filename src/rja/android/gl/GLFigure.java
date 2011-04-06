package rja.android.gl;

import javax.microedition.khronos.opengles.GL10;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class GLFigure {

    private IntBuffer   mVertexBuffer;
    private IntBuffer   mColorBuffer;
    private IntBuffer   mNormalBuffer;
    private ByteBuffer  mIndexBuffer;

	private int pointsToDraw = 6;

	private boolean cullBackSurface = false;

	public GLFigure() {
        int one = 0x10000;
        int onePointOne = 0x11000;
        int vertices[] = {
                -one, -one, -one,
                one, -one, -one,
                one,  one, -one,
                -one,  one, -one,
                -one, -one,  one,
                one, -one,  one,
                one,  one,  one,
                -one,  one,  one,

                -one, -one, -one,
                one, -one, -one,
                one,  one, -one,
                -one,  one, -one,
                -one, -one,  one,
                one, -one,  one,
                one,  one,  one,
                -one,  one,  one,

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
			one,    0,    0,  one, // Red
			one,    0,    0,  one, // Red
			0,  one,    0,  one, // Green
			0,  one,    0,  one, // Green
			one,    0,    0,  one, // Red
			one,    0,    0,  one, // Red
			0,  one,    0,  one, // Green
			0,  one,    0,  one, // Green
			
			0,    0,  one,  one, //Blue 
			one,  one,    0,  one, // Yellow
			one,  one,    0,  one, // Yellow
			0,    0,  one,  one, //Blue 
			0,    0,  one,  one, //Blue 
			one,  one,    0,  one, // Yellow
			one,  one,    0,  one, // Yellow
			0,    0,  one,  one, //Blue 
			
			0,  one,  one,  one, // Cyan
			0,  one,  one,  one, // Cyan
			0,  one,  one,  one, // Cyan
			0,  one,  one,  one, // Cyan
			one,    0,  one,  one, // Magenta
			one,    0,  one,  one, // Magenta
			one,    0,  one,  one, // Magenta
			one,    0,  one,  one, // Magenta
		};

		int normals[] = {
			one, -one, 0, one, -one, 0, one, -one, 0,
			0, -one, 0, 0, -one, 0, 0, -one, 0,
			one, 0, 0, one, 0, 0, one, 0, 0,
			one, 0, 0, one, 0, 0, one, 0, 0,
			0, one, 0, 0, one, 0, 0, one, 0,
			0, one, 0, 0, one, 0, 0, one, 0,
			-one, 0, 0, -one, 0, 0, -one, 0, 0,
			-one, 0, 0, -one, 0, 0, -one, 0, 0,
			0, 0, one, 0, 0, one, 0, 0, one,
			0, 0, one, 0, 0, one, 0, 0, one,
			0, 0, -one, 0, 0, -one, 0, 0, -one,
			0, 0, -one, 0, 0, -one, 0, 0, -one,
		};

        byte indices[] = {
			0      , 4      , 5      ,   0      ,  5     , 1      , // Red
			1+   8 , 5+   8 , 6+   8 ,   1+   8 ,  6+  8 , 2+   8 , // Yellow
            2      , 6      , 7      ,   2      ,  7     , 3      , // Green
            3+(1*8), 7+(1*8), 4+(1*8),   3+(1*8), 4+(1*8), 0+(1*8), // Blue
            4+(2*8), 7+(2*8), 6+(2*8),   4+(2*8), 6+(2*8), 5+(2*8), // Magenta
            3+(2*8), 0+(2*8), 1+(2*8),   3+(2*8), 1+(2*8), 2+(2*8)  // Cyan
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

        ByteBuffer nbb = ByteBuffer.allocateDirect(normals.length*4);
        nbb.order(ByteOrder.nativeOrder());
        mNormalBuffer = nbb.asIntBuffer();
        mNormalBuffer.put(normals);
        mNormalBuffer.position(0);

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

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        gl.glVertexPointer(3, gl.GL_FIXED, 0, mVertexBuffer);
        gl.glColorPointer(4, gl.GL_FIXED, 0, mColorBuffer);
        gl.glNormalPointer(3, gl.GL_FIXED, mNormalBuffer);
        gl.glDrawElements(gl.GL_TRIANGLES, pointsToDraw, gl.GL_UNSIGNED_BYTE, mIndexBuffer);

		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
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
