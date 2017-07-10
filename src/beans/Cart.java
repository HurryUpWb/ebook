package beans;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private double TotalMoney=0;
	private Map<Book,Integer> BookMap=new HashMap<>();
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	
	public double getTotalMoney() {
		for(Map.Entry<Book,Integer> entry:BookMap.entrySet()){
			TotalMoney+=entry.getKey().getB_price()*entry.getValue();
		}
		return TotalMoney;
	}
	
	public Map<Book, Integer> getBookMap() {
		return BookMap;
	}
	
	public void AddBook(Me m,Book b,Integer num,String flag){
		if(m.getCart().getBookMap().size()>0){
			for(Map.Entry<Book,Integer> map:m.getCart().getBookMap().entrySet()){
				if(map.getKey().getB_id().equals(b.getB_id())){
					if(flag!=null && flag!="" && "add".equals(flag)){
						map.setValue(num);
						return;
					}
					map.setValue(map.getValue()+num);
					return;
				}
			}
		}
		BookMap.put(b, num);
	}
	
	public Integer DropBook(Book b){
		Integer i=0;
		Book bb=null;
		for(Map.Entry<Book,Integer> map:getBookMap().entrySet()){
			if(map.getKey().getB_id().equals(b.getB_id())){
				bb=map.getKey();
				break;
			}
		}
		if(bb!=null){
			i=getBookMap().remove(bb);
		}
		return i;
	}
}
