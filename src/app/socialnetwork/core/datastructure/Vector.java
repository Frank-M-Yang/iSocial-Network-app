package app.socialnetwork.core.datastructure;

public class Vector {
	private Object data[];
	private int count;
	
    public Vector(int capacity) {
        data = new Object[capacity];
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Object get(int index) {
        return data[index];
    }

    public void set(int index, Object obj) {
        data[index] = obj;
    }

    public boolean contains(Object obj) {
        for(int i=0;i<count;i++) {
            if(data[i] == obj) return true;
        }
        return false;
    }
    
    public void addFirst(Object item) {
    	for(int i =count;i>0;i++) {
    		data[i]=data[i-1];
    }
	data[0]=item;
	count++;
    }
    
    public void addLast(Object item) {
    	data[count]=item;
    	count++;
    	
    }
    
    public Object getFirst() {
    	if (count>0) {
    		return data[0];
    	}
    	return null;
    }
    
    public Object getLast() {
    	if (count>0) {
    		return data[count-1];
    	}
    	return null;
    }
    
    public void removeFirst() {
    	if (count>0) {
    		for(int i=0;i<count-1;i++) {
    			data[i]=data[i+1];
    		}
    		
    	data[count-1]=null;
    	count--;
    	}
    }
    
    public void removeLast() {
    	if(count>0) {
    	data[count-1]=null;
    	count--;
    	}
    }
    
    
    public boolean binarySearch(Comparable key)
    {
        int start = 0;
        int end = count - 1;
        while(start <= end)
        {
            int middle = (start + end) / 2;
            int comparison = key.compareTo(data[middle]);
            
            if(comparison < 0) 
                end = middle - 1;
            else if(comparison > 0) 
                start = middle + 1;
            else 
                return true;
        }
        return false;
    }
    
    
    
    //repeat
    
    public Vector repeat() {
    	
    	Vector result = new Vector(count*2);
    	
    	for(int i=0;i<count;i++)
    	{
    		result.addLast(data[i]);
    		result.addLast(data[i]);
    	} 
    	return result;
    }
    
    public Vector interleaveExtended(Vector v2) {
    	int minSize;
    	int maxSize;
    	
    	if(count<v2.size()) {
    		minSize = count;
    		maxSize = v2.size();
    		
    	}
    	
    	else {
    		minSize = v2.size();
    		maxSize = count;
    	}
    	
    	Vector result = new Vector(count+v2.size());
    	
    	for(int i=0;i<minSize;i++) {
    		result.addLast(data[i]);
    		result.addLast(v2.get(i));
    		
    	}
    	
    	if (count>v2.size()) {
    		for (int i=minSize;i<maxSize;i++) {
    			result.addLast(data[i]);
    		}
    	}
    	
    	if(count<v2.size()) {
    		for (int i = minSize; i<maxSize;i++) {
    			result.addLast(v2.get(i));
    		}
    	}
    	return result;
    }
    	
    
    

}
