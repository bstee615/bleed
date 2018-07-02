package util;

import Drip.DripInfo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Utility class to add union and garbage collection functionality to DripSet.
 * 
 * @author Benjamin Steenhoek
 * @version 1
 */
public class DripSet extends HashSet<DripInfo> {
    private int maxElements;

    public DripSet()
    {
        super();
    }

    public DripSet(int maxElements)
    {
        super();
        this.maxElements = maxElements;
    }

//region Set arithmetic
//---------------------------------------------------------------------------------------
    public void union(Set<DripInfo> s){
        addAll(s);
        clampElements();
    }
//---------------------------------------------------------------------------------------
//endregion

//region Garbage collection
//---------------------------------------------------------------------------------------
    private void clampElements()
    {
        if (maxElements == 0)
        {
            return;
        }

        int difference = size() - maxElements;
        if (difference <= 0)
        {
            return;
        }

        markElementsToRemove(difference);
    }

    private void markElementsToRemove(int difference)
    {
        DripInfo currentItem = null;
        int counter = 0;
        for (Iterator it = iterator(); it.hasNext(); currentItem = (DripInfo) it.next())
        {
            if (counter / difference == 1) {
                currentItem.markToRemove();
            }
            counter ++;
        }
    }

    public int getMaxElements()
    {
        return maxElements;
    }
//---------------------------------------------------------------------------------------
//endregion
}
