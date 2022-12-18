package queue;

import javax.swing.JOptionPane;

public class Client {

	public static void main(String[] args) {
		Queue<Person> queue = new Queue<Person>();

		String menu = "1. Chegada de pessoa na fila\n" + "2. Atender pessoa\n" + "3. Mostrar pessoas na fila\n"
				+ "4. Gerar estatísticas da fila\n" + "5. Sair";
		int option = -1;
		String name;
		int age;
		Person p;

		while (option != 5) {
			option = Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch (option) {
			case 1:
				name = JOptionPane.showInputDialog("Nome?");
				age = Integer.parseInt(JOptionPane.showInputDialog("Idade?"));
				while(age<=0 || age >130) {
					age = Integer.parseInt(JOptionPane.showInputDialog("Idade inválida. Por favor, digite novamente."));
				}
				p = new Person(name, age);
				if(age >= 60) {
					queue.priorityEnqueue(p);
				}else{
					queue.enqueue(p);
				}
				break;
			case 2:
				try {
					p = (Person) queue.dequeue();
					JOptionPane.showMessageDialog(null, p.getName() + "\n" + p.getAge());
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				break;
			case 3:
				JOptionPane.showMessageDialog(null, queue.showQueue());
				break;
			case 4:
				JOptionPane.showMessageDialog(null, queue.showStats());
				break;
			default:
				return;
			}
		}
	}
}
