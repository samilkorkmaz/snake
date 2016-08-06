package snake;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author skorkmaz
 */
public class ModelTest {
    
    @Test
    public void testGet1DIndex_1() {
        int iRow = 0;
        int iCol = 0;
        int nRow = 0;
        int expResult = 0;
        int result = Model.get1DIndex(iRow, iCol, nRow);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGet1DIndex_2() {
        int iRow = 1;
        int iCol = 2;
        int nRow = 4;
        int expResult = 9;
        int result = Model.get1DIndex(iRow, iCol, nRow);
        assertEquals(expResult, result);
    }

    @Test
    public void testGet2DIndices_1() {
        int index1D = 0;
        int[] expResult = {0,0};
        int[] result = Model.get2DIndices(index1D, 4);
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testGet2DIndices_2() {
        int index1D = 7;
        int[] expResult = {1, 2};
        int[] result = Model.get2DIndices(index1D, 3);
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testGet2DIndices_3() {
        int index1D = 13;
        int[] expResult = {3, 2};
        int[] result = Model.get2DIndices(index1D, 5);
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testGet2DIndices_4() {
        int index1D = 4;
        int[] expResult = {4, 0};
        int[] result = Model.get2DIndices(index1D, 5);
        assertArrayEquals(expResult, result);
    }
    
    @Test
    public void testGet2DIndices_5() {
        int index1D = 5;
        int[] expResult = {0, 1};
        int[] result = Model.get2DIndices(index1D, 5);
        assertArrayEquals(expResult, result);
    }
}
