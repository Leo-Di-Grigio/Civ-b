package recources.gl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL2;

public class VBO {
	
	int totalNumVerts;
	int vbo;

	int vertexStride;
	int colorPointer;
	int vertexPointer;
	   
	public VBO(GL2 gl, float [][][] terrain, int sizeX, int sizeY) {
		totalNumVerts = sizeX * sizeY * 3;

	    // generate a VBO pointer / handle
		IntBuffer buf = IntBuffer.allocate(1);
	    gl.glGenBuffers(1, buf);
	    vbo = buf.get();

	    // interleave vertex / color data
	    FloatBuffer data = FloatBuffer.allocate(sizeX * sizeY * 3);
	      
	    for (int i = 0; i < sizeX; i++) {
	       for (int j = 0; j < sizeY; j++) {
	          data.put(terrain[i][j][0]);
	          data.put(terrain[i][j][1]);
	          data.put(terrain[i][j][2]);
	       }
	    }
	    data.rewind();

	    int bytesPerFloat = Float.SIZE / Byte.SIZE;

	    // transfer data to VBO
	    int numBytes = data.capacity() * bytesPerFloat;
	    gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, vbo);
	    gl.glBufferData(GL2.GL_ARRAY_BUFFER, numBytes, data, GL2.GL_STATIC_DRAW);
	    gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);

	    vertexStride = 6 * bytesPerFloat;
	    colorPointer = 0;
	    vertexPointer = 3 * bytesPerFloat;
	}

	public void draw(GL2 gl) {
		gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, vbo);

	    gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
	    gl.glEnableClientState(GL2.GL_COLOR_ARRAY);

	    gl.glColorPointer(3, GL2.GL_FLOAT, vertexStride, colorPointer);
	    gl.glVertexPointer(3, GL2.GL_FLOAT, vertexStride, vertexPointer);

	    gl.glDrawArrays(GL2.GL_QUADS, 0, totalNumVerts);

	    gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
	    gl.glDisableClientState(GL2.GL_COLOR_ARRAY);

	    gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, 0);
	}
}
