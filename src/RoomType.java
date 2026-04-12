import java.util.ArrayList;

public class RoomType
{
    private ArrayList<String> types;
    private ArrayList<Double> prices;

    public RoomType()
    {
        this.types = new ArrayList<>();
        this.prices = new ArrayList<>();
        this.types.add("Single");
        this.types.add("Double");
        this.types.add("Suite");
        this.prices.add(100.00);
        this.prices.add(200.00);
        this.prices.add(300.00);

    }
    public RoomType(ArrayList<String> types , ArrayList<Double> prices)
    {
        this.types = types;
        this.prices = prices;
    }

    //Getters
    public ArrayList<String> getTypes()
    {
        return this.types;
    }
        public ArrayList<Double> getPrices()
    {
        return this.prices;
    }

    //Setters
    public void setTypes(ArrayList<String> t)
    {
        this.types = t;
    }

    public void setPrices(ArrayList<Double> p)
    {
        this.prices = p;
    }



    public void updatePrices(Double Oldprice , Double Newprice) // updates old price and replaces it with new price
    {

        int index = this.prices.indexOf(Oldprice);

        if (index != -1)
        {
            this.prices.set(index, Newprice);
        }
    }

    public void addType(String type, Double price){
        types.add(type);
        prices.add(price);
    }

    public void removeType(String type){
        int index = types.indexOf(type);
        types.remove(index);
        prices.remove(index);
    }
}
