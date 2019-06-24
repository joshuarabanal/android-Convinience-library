package android.media;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class AudioRecord {
	private TargetDataLine line;
	private AudioInputStream in;

	public AudioRecord(int audioSource, int sampleRateInHz, int channelConfig, int audioFormat, int bufferSizeInBytes) {
		if(audioFormat != android.media.AudioFormat.ENCODING_PCM_16BIT) { throw new IndexOutOfBoundsException("wrong encoding"); }
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = true;
		AudioFormat format = new AudioFormat(sampleRateInHz, sampleSizeInBits,
                channels, signed, bigEndian);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
	        line.open(format);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			IndexOutOfBoundsException x=  new IndexOutOfBoundsException("failed to open audio source");
			x.initCause(e);
			throw x;
		}
	}
	
	public void startRecording() {
        line.start();   // start capturing
        in = new AudioInputStream(line);
	}
	public int read(short[] buf, int start, int length) throws IOException {
		for(int i = 0; i<length; i++) {
			int read = read();
			if(read < 0) {
				return i;
			}
			buf[start+i] = (short) read;
		}
		return length;
	}
	public int read(short[] buf) throws IOException {
		return read(buf,0,buf.length);
	}
	public  int read() throws IOException {
        int first = in.read();
        int second = in.read();
        if(first<0 || second<0){
            return -1;
        }
        return ((second&0xff)<<8) | (first& 0xff);
    }
}
