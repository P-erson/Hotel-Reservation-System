import java.util.ArrayList;

public class RoomType
{
    private ArrayList<String> types = new ArrayList<>();
    private ArrayList<Double> prices = new ArrayList<>();

    public RoomType()
    {
        // this.types = new ArrayList<>();
        // this.prices = new ArrayList<>();
        this.types.add("Single");
        this.types.add("Double");
        this.types.add("Suite");
        this.prices.add(100.00);
        this.prices.add(200.00);
        this.prices.add(300.00);

    }
    // public RoomType(ArrayList<String> types , ArrayList<Double> prices)
    // {
    //     this.types = types;
    //     this.prices = prices;
    // }

    //Getters
    public ArrayList<String> getTypes() { return types; }
    public ArrayList<Double> getPrices() { return prices; }

    //Setters
    public void setTypes(ArrayList<String> t) { types = t; }
    public void setPrices(ArrayList<Double> p) { prices = p; }



    private void updateType(String oldType, String newType) // updates old price and replaces it with new price
    {
        int index = types.indexOf(oldType);

        if (index != -1)
        {
            types.set(index, newType);
        }
    }

    private void updatePrices(Double Oldprice , Double Newprice) // updates old price and replaces it with new price
    {
        int index = this.prices.indexOf(Oldprice);

        if (index != -1)
        {
            this.prices.set(index, Newprice);
        }
    }


    public void update(String oldType, String newType, Double oldPrice, Double newPrice){
        updateType(oldType, newType);
        updatePrices(oldPrice, newPrice);
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
