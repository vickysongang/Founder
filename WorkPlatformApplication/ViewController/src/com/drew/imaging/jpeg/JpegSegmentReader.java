package com.drew.imaging.jpeg;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class JpegSegmentReader {
    private final File _file;
    private final byte[] _data;
    private final InputStream _stream;
    private JpegSegmentData _segmentData;
    private static final byte SEGMENT_SOS = -38;
    private static final byte MARKER_EOI = -39;
    public static final byte SEGMENT_APP0 = -32;
    public static final byte SEGMENT_APP1 = -31;
    public static final byte SEGMENT_APP2 = -30;
    public static final byte SEGMENT_APP3 = -29;
    public static final byte SEGMENT_APP4 = -28;
    public static final byte SEGMENT_APP5 = -27;
    public static final byte SEGMENT_APP6 = -26;
    public static final byte SEGMENT_APP7 = -25;
    public static final byte SEGMENT_APP8 = -24;
    public static final byte SEGMENT_APP9 = -23;
    public static final byte SEGMENT_APPA = -22;
    public static final byte SEGMENT_APPB = -21;
    public static final byte SEGMENT_APPC = -20;
    public static final byte SEGMENT_APPD = -19;
    public static final byte SEGMENT_APPE = -18;
    public static final byte SEGMENT_APPF = -17;
    public static final byte SEGMENT_SOI = -40;
    public static final byte SEGMENT_DQT = -37;
    public static final byte SEGMENT_DHT = -60;
    public static final byte SEGMENT_SOF0 = -64;
    public static final byte SEGMENT_COM = -2;

    public JpegSegmentReader(File paramFile) throws JpegProcessingException {
        this._file = paramFile;
        this._data = null;
        this._stream = null;
        readSegments();
    }

    public JpegSegmentReader(byte[] paramArrayOfByte) throws JpegProcessingException {
        this._file = null;
        this._data = paramArrayOfByte;
        this._stream = null;
        readSegments();
    }

    public JpegSegmentReader(InputStream paramInputStream) throws JpegProcessingException {
        this._stream = paramInputStream;
        this._file = null;
        this._data = null;
        readSegments();
    }

    public JpegSegmentReader(JpegSegmentData paramJpegSegmentData) {
        this._file = null;
        this._data = null;
        this._stream = null;
        this._segmentData = paramJpegSegmentData;
    }

    public byte[] readSegment(byte paramByte) throws JpegProcessingException {
        return readSegment(paramByte, 0);
    }

    public byte[] readSegment(byte paramByte, int paramInt) {
        return this._segmentData.getSegment(paramByte, paramInt);
    }

    public final int getSegmentCount(byte paramByte) {
        return this._segmentData.getSegmentCount(paramByte);
    }

    public final JpegSegmentData getSegmentData() {
        return this._segmentData;
    }

    private void readSegments() throws JpegProcessingException {
        this._segmentData = new JpegSegmentData();
        BufferedInputStream localBufferedInputStream = getJpegInputStream();
        try {
            int i = 0;
            if (!isValidJpegHeaderBytes(localBufferedInputStream))
                throw new JpegProcessingException("not a jpeg file");
            i += 2;
            int j = (byte)(localBufferedInputStream.read() & 0xFF);
            if ((j & 0xFF) != 255)
                throw new JpegProcessingException("expected jpeg segment start identifier 0xFF at offset " + i +
                                                  ", not 0x" + Integer.toHexString(j & 0xFF));
            i++;
            int k = (byte)(localBufferedInputStream.read() & 0xFF);
            i++;
            byte[] arrayOfByte1 = new byte[2];
            localBufferedInputStream.read(arrayOfByte1, 0, 2);
            i += 2;
            int m = arrayOfByte1[0] << 8 & 0xFF00 | arrayOfByte1[1] & 0xFF;
            m -= 2;
            //            if (m > localBufferedInputStream.available())
            //                throw new JpegProcessingException("segment size would extend beyond file stream length aaaa");
            if (m < 0)
                throw new JpegProcessingException("segment size would be less than zero");
            byte[] arrayOfByte2 = new byte[m];
            localBufferedInputStream.read(arrayOfByte2, 0, m);
            i += m;
            //      if ((k & 0xFF) == 218)
            //        jsr 74;
            //      if ((k & 0xFF) == 217)
            //        jsr 58;
            this._segmentData.addSegment((byte)k, arrayOfByte2);
        } catch (IOException localIOException1) {
            throw new JpegProcessingException("IOException processing Jpeg file: " + localIOException1.getMessage(),
                                              localIOException1);
        } finally {
            try {
                if (localBufferedInputStream != null)
                    localBufferedInputStream.close();
            } catch (IOException localIOException2) {
                throw new JpegProcessingException("IOException processing Jpeg file: " +
                                                  localIOException2.getMessage(), localIOException2);
            }
        }
    }

    private BufferedInputStream getJpegInputStream() throws JpegProcessingException {
        if (this._stream != null) {
            if ((this._stream instanceof BufferedInputStream))
                return (BufferedInputStream)this._stream;
            return new BufferedInputStream(this._stream);
        }
        Object localObject;
        if (this._data == null)
            try {
                localObject = new FileInputStream(this._file);
            } catch (FileNotFoundException localFileNotFoundException) {
                throw new JpegProcessingException("Jpeg file does not exist", localFileNotFoundException);
            }
        else
            localObject = new ByteArrayInputStream(this._data);
        return new BufferedInputStream((InputStream)localObject);
    }

    private boolean isValidJpegHeaderBytes(InputStream paramInputStream) throws IOException {
        byte[] arrayOfByte = new byte[2];
        paramInputStream.read(arrayOfByte, 0, 2);
        return ((arrayOfByte[0] & 0xFF) == 255) && ((arrayOfByte[1] & 0xFF) == 216);
    }
}
