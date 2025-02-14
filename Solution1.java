class ProductOfNumbers {

    List<Integer> l;
    List<Integer> prefix;
    Set<Integer> zero;
    public ProductOfNumbers() {
        l=new ArrayList<>();
        zero=new HashSet<>();
        prefix=new ArrayList<>();
    }
    
    public void add(int num) {
        l.add(num);
        if(num==0)
            zero.add(l.size()-1);
        if(prefix.isEmpty())
            prefix.add(num);
        else
        {
            if(prefix.get(prefix.size()-1)==0)
                prefix.add(num);
            else
                prefix.add(prefix.get(prefix.size()-1)*num);
        }
    }
    
    public int getProduct(int k) {
        for(int a:zero)
        {
            if(a>=l.size()-k && a<l.size())
                return 0;
        }
        if(l.size()-k-1>=0 && prefix.get(l.size()-k-1)!=0)
            return prefix.get(prefix.size()-1)/prefix.get(l.size()-k-1);
        return prefix.get(prefix.size()-1);
    }

}