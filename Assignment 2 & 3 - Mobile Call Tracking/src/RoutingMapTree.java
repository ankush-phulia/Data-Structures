import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class RoutingMapTree {

	Exchange root;
	
	public RoutingMapTree(){
		this.root=new Exchange(0);
	}
	public RoutingMapTree(Exchange e){
		this.root=e;
	}
	
	public boolean isEmpty(){
		return (this.root==null);
	}
	
	public boolean containsNode(int a) throws ExchangeException{
		if (isEmpty()){
			return false;
		}
		else if (root.id==a){
			return true;
		}
		else if(root.children.l.isEmpty()){
			return false;
		}
		else{
			for (int i=0;i<this.root.numChildren();i++){
				RoutingMapTree t=new RoutingMapTree(this.root.child(i));
				if (t.containsNode(a)){
					return true;
				}
			}
			return false;
		}
	}
	public Exchange getNode(int x) throws ExchangeException{
		if (isEmpty()){
			return null;
		}
		else if (root.numChildren()==0){
			if (root.id==x){
				return root;
			}
			else return null;
		}
		else if (root.id==x){
			return root;
		}
		else{
			for (int i=0;i<this.root.numChildren();i++){
				RoutingMapTree t=new RoutingMapTree(this.root.child(i));
				Exchange e=t.getNode(x);
				if (e!=null){
					return e;
				}
			}
			return null;
		}
	}
/*	
	public void switchOn(MobilePhone a,Exchange b){
		a.switchOn();
		b.mobset.insert(a);
	}
	public void switchOff(MobilePhone a) throws SetException, MobilePhoneException{
		a.switchOff();
		Exchange b=a.location();
		b.mobset.delete(a);
	}*/
	
	public void addExchange(int a,int b) throws ExchangeException{
		if (this.containsNode(a)){
			this.getNode(a).children.add(new Exchange(b));
//			System.out.println("ExDone");
		}
		else{
			System.out.println("Exchange with given id does not Exist");
		}

	}
	
	public void switchOnMobile(int a,int b) throws ExchangeException{
		if (this.containsNode(b)){
			if (getNode(b).numChildren()==0){
				if (this.root.containsPhone(a)){
					System.out.println(a);
					System.out.println("Phone already Exists");
				}
				else{
					Exchange e=this.getNode(b);
					boolean chk=false;
					Iterator<MobilePhone> it=e.mobset.a.l.iterator();
					while (it.hasNext()){
						MobilePhone m=it.next();
						if (m.id==a){
							chk=true;
							m.switchOn();
//							System.out.println("EDone");
							break;
						}
					}
					if (!chk){
						MobilePhone n=new MobilePhone(a,e);
//						System.out.println("ADone");
					}
				}
			}
			else{
				System.out.println("This isn't a Zero Level Exchange");
			}
		}
		else{
			System.out.println("Exchange with given id does not Exist");
		}
	}
	public void switchOffMobile(int a) throws ExchangeException, SetException{
		if (isEmpty()){
			System.out.println("Mobile with given id does not Exist");
		}
		else if (root.containsPhone(a)){
			root.getPhone(a).switchOff();
		}
		else if(root.children.l.isEmpty()){
			System.out.println("Mobile with given id does not Exist");
		}
		else{
			boolean chk=false;
			for (int i=0;i<this.root.numChildren();i++){
				RoutingMapTree t=new RoutingMapTree(this.root.child(i));
				boolean e=t.root.containsPhone(a);
				if (e){
					t.switchOffMobile(a);
					chk=true;
					/*System.out.println("OffDone");*/
					break;
				}
			}
			if (!chk){
				System.out.println("Mobile with given id does not Exist");

			}
		}
	}
	
	public void queryNthChild(int a,int b) throws ExchangeException {
		Exchange e=this.getNode(a);
		if (e==null){
			System.out.println("Node does not Exist in the tree");
		}
		else{
			System.out.println(e.child(b).id);
//			System.out.println("QNDone");
		}
	}
	public void queryMobilePhoneSet(int a) throws ExchangeException {
		Exchange e=this.getNode(a);
		if (e==null){
			System.out.println("Node does not Exist in the tree");
		}
		else{
			Iterator<MobilePhone> it=e.mobset.a.l.iterator();
			while (it.hasNext()){
				MobilePhone p=it.next();
				if (p.status){
					System.out.print(p.number() + " ");
				}
			}
			System.out.println(" ");
//			System.out.println("QMDone");
		}
	}
	
	public void performAction(String ActionMessage) throws ExchangeException, SetException, MobilePhoneException{
		String[] str=ActionMessage.split(" ");
		if (str[0].substring(0,3).contentEquals("add")){
			if (str.length==3){
				int a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				this.addExchange(a,b);
			}
			else{
				System.out.println("An Input is Missing");
			}

		}
		else if (str[0].substring(0,8).contentEquals("switchOn")){
			if (str.length==3){
				int a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				this.switchOnMobile(a,b);
			}
			else{
				System.out.println("An Input is Missing");
			}
		}
		else if (str[0].substring(0,8).contentEquals("switchOf")){
			if (str.length==2){
				int a=Integer.parseInt(str[1]);
				this.switchOffMobile(a);
			}
			else{
				System.out.println("Invalid Input");
			}
		}
		else if (str[0].substring(0,6).contentEquals("queryN")){
			if (str.length==3){
				int a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				this.queryNthChild(a,b);
			}
			else{
				System.out.println("An Input is Missing");
			}
		
		}
		else if (str[0].substring(0,6).contentEquals("queryM")){
			if (str.length==2){
				int a=Integer.parseInt(str[1]);
				this.queryMobilePhoneSet(a);
			}
			else{
				System.out.println("Invalid Input");
			}
		}
		else if (str[0].substring(0,3).equals("low")){
			if (str.length==3){
				int a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				Exchange e=this.lowestRouter(a,b);
				if (e!=null){
					System.out.println(e.id);
				}
				else{
					System.out.println("A node is Missing");
				}
			}
			else{
				System.out.println("An Input is Missing");
			}
		}
		else if (str[0].substring(0,6).contentEquals("findPh")){
			if (str.length==2){
				int a=Integer.parseInt(str[1]);
				Exchange e=this.findPhone(a);
				if (e!=null){
					System.out.println(e.id);
				}
				else{
					System.out.println("Phone is Not Present");
				}
			}
			else{
				System.out.println("Invalid Input");
			}
		}
		else if (str[0].substring(0,6).contentEquals("movePh")){
			if (str.length==3){
				int a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				this.movePhone(a,b);
			}
			else{
				System.out.println("An Input is Missing");
			}
		}
		else if (str[0].substring(0,8).equals("findCall")){
			if (str.length==3){
				int a=Integer.parseInt(str[1]);
				int b=Integer.parseInt(str[2]);
				LinkedList<Exchange> e=this.findCallPath(a, b);
				if (e!=null){
					Iterator<Exchange> it=e.iterator();
					while (it.hasNext()){
						System.out.print(it.next().id+" ");
					}
					System.out.println(" ");
				}
				else{
					System.out.println("A Phone is missing");
				}
			}
			else{
				System.out.println("An Input is Missing");
			}
		}
		
		else{
			System.out.println("Invalid Command");
		}
	}
	
	public Exchange findPhone(int m) throws MobilePhoneException{
		Iterator<MobilePhone> it=this.root.mobset.a.l.iterator();
		while (it.hasNext()){
			MobilePhone p=it.next();
			if (p.status && p.id==m){
				return p.location();
			}
		}
		return null;
	}
	
	public Exchange lowestRouter(int a,int b) throws ExchangeException{
		if (!this.containsNode(a)|| !this.containsNode(b)){
			System.out.println("Not found");
			return null;
		}
		Exchange e1=this.getNode(a);
		Exchange e2=this.getNode(b);
		if (e1.equals(e2)){
			return this.getNode(a);
		}
		else{
			if (e1.isRoot()){
				return e1;
			}
			else if(e2.isRoot()){
				return e2;
			}
			else{
				RoutingMapTree r1=new RoutingMapTree(e1);
				RoutingMapTree r2=new RoutingMapTree(e2);
				if (r1.containsNode(b)){
					return e1;
				}
				else if (r2.containsNode(a)){
					return e2;
				}
				else{
					return lowestRouter(e1.parent.id,e2.parent.id);					
				}

			}
		}
		
	}
	
	public LinkedList<Exchange> findCallPath(int a,int b) throws ExchangeException, MobilePhoneException{
		if (this.findPhone(a).equals(null) || this.findPhone(b).equals(null)){
			return null;
		}
		else if (a==b){
			LinkedList<Exchange> exch=new LinkedList<Exchange>();
			exch.add(findPhone(a));
			return exch;
		}
		else{
			Exchange r=findPhone(a);
			Exchange p=findPhone(b);
			Exchange e=this.lowestRouter(r.id, p.id);
			LinkedList<Exchange> exch=new LinkedList<Exchange>();
			LinkedList<Exchange> exch2=new LinkedList<Exchange>();
			LinkedList<Exchange> exch3=new LinkedList<Exchange>();
			if (r.equals(e)){
				exch.add(r);
				r=r.parent();
			}
			else{
				exch.add(r);
				while (r.id!=e.id){
					r=r.parent;
					exch.add(r);
				}
			}		
			while (!p.equals(e)){
				exch2.add(p);
				p=p.parent();
			}
			Iterator<Exchange> it3=exch2.iterator();
			while (it3.hasNext()){
				exch3.addFirst(it3.next());
			}			
			Iterator<Exchange> it=exch3.iterator();
			while (it.hasNext()){
				Exchange tmp=it.next();
				exch.add(tmp);
			}
			return exch;
		}
		
	}
	
	public void movePhone(int a,int b) throws ExchangeException, MobilePhoneException, SetException{
		Exchange e=this.getNode(b);
		if (e==null){
			System.out.println("Node does not Exist in the tree");
		}
		
		else if (findPhone(a)==null){
			System.out.println("Phone does not Exist/Is switched off");
		}
		else{
			if (getNode(b).numChildren()==0){
				this.switchOffMobile(a);
				this.switchOnMobile(a, b);
			}
			else{
				System.out.println("This isn't a zero level Exchange");
			}
		}
	}	
	
	
	public static void main(String[] args) throws SetException, ExchangeException, MobilePhoneException{
		Exchange e=new Exchange(10);
		Exchange e1=new Exchange(1,e);
		Exchange e2=new Exchange(2,e);
		Exchange e3=new Exchange(3,e);
		Exchange e4=new Exchange(4,e1);
		Exchange e5=new Exchange(5,e2);
		RoutingMapTree rtm=new RoutingMapTree();
		rtm.root.children.add(e);
		MobilePhone m1=new MobilePhone(123,e1);
		MobilePhone m2=new MobilePhone(124,e2);
		MobilePhone m3=new MobilePhone(125,e1);
		MobilePhone m5=new MobilePhone(127,e4);
		MobilePhone m4=new MobilePhone(128,e5);
		rtm.switchOnMobile(126,2);
		System.out.println(rtm.root.child(0).getPhone(126).status());
		rtm.switchOnMobile(126,2);
		System.out.println(rtm.root.child(0).getPhone(126).status());
		rtm.queryMobilePhoneSet(0);
		System.out.println(rtm.containsNode(2));
		rtm.movePhone(126, 3);
		System.out.println(rtm.findPhone(127));
		System.out.println(rtm.findPhone(127).parent.parent.id);
		System.out.println(rtm.findCallPath(127,128));
		System.out.println(rtm.findCallPath(128,127));
	}
	
}
