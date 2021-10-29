package es.florida.ad;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class ActEva3 
{
	// Este metode ens permitira fer un ArrayList de tots el llibres que tenim en la llista i guardarlos.
	public static ArrayList<Llibre> Llibres = new ArrayList<Llibre>();
	
	// Este metode ens permitira verificar lo que anem a escriure nosaltres per consola.
	static Scanner teclat = new Scanner(System.in);
	
	// Este metode ens permitira a traves de uns strings, un constructor i getters i setters,
	// crear llibres nous i afegirlos a la llista de llibres del ArrayList.
	public static class Llibre 
	{
		private String id,titol,autor,anypubli,editorial,nombrepag;
		
		public Llibre(String id, String titol, String autor, String anypubli, String editorial, String nombrepag) 
		{
			this.id = id;
			this.titol = titol;
			this.autor = autor;
			this.anypubli = anypubli;
			this.editorial = editorial;
			this.nombrepag = nombrepag;
		}
		
		public String getId() 
		{
			return id;
		}
		
		public void setId(String id) 
		{
			this.id = id;
		}
		
		public String getTitol() 
		{
			return titol;
		}
		
		public String setTitol(String titol) 
		{
			return this.titol = titol;
		}
		
		public String getAutor() 
		{
			return autor;
		}
		
		public String setAutor(String autor) 
		{
			return this.autor = autor;
		}
		
		public String getAnyPubli() 
		{
			return anypubli;
		}
		
		public String setAnyPubli(String anypubli) 
		{
			return this.anypubli = anypubli;
		}
		
		public String getEditorial() 
		{
			return editorial;
		}
		
		public String setEditorial(String editorial) 
		{
			return this.editorial = editorial;
		}
		
		public String getNombrepag() 
		{
			return nombrepag;
		}
		
		public String setNombrepag(String nombrepag) 
		{
			return this.nombrepag = nombrepag;
		}
	}
	
	// Este metode ens permitira recorrer la llista de llibres i afegirlos al ArrayList a traves del metode recuperarTots,
	// i si el id que nosotres preguntem per pantalla es el mateix que esta en este metode, ens traura el id i el titol,
	// de eixe llibre.
	public static int recuperarLlibre(int identificaor) 
	{
		ArrayList<Llibre> Llibrests = recuperarTots();
		
		for(Llibre lli : Llibrests) 
		{
			if(Integer.parseInt(lli.getId()) == identificaor) 
			{
				System.out.println("Id del llibre: " + lli.getId());
				System.out.println("Titol del llibre: " + lli.getTitol());
			}
		}
		return identificaor;
	}
	
	// Este metode ens permitira mostrar tota la informacio dels llibres que hi ha en la llista del ArrayList.
	public static void mostrarLlibre(Llibre llibres) 
	{
		System.out.println("Id del llibre: " + llibres.getId());
		
		System.out.println("Titol del llibre: " + llibres.getTitol());
					
		System.out.println("Autor del llibre: " + llibres.getAutor());
					
		System.out.println("Any de publicacio del llibre: " + llibres.getAnyPubli());
				
		System.out.println("Editorial del llibre: " + llibres.getEditorial());
					
		System.out.println("Nombre de pagines del llibre: " + llibres.getNombrepag());
	}
	
	// Este metode ens permitira recorrer tota la lista de llibres del ArrayList i afegirlos a la llista.
	public static ArrayList<Llibre> recuperarTots() 
	{		
		try 
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("llibres.xml"));
			
			Element raiz = document.getDocumentElement();
			System.out.println("");
			
			NodeList nodeList = document.getElementsByTagName("llibre");

			
			for (int i = 0; i < nodeList.getLength(); i++) 
			{
				Node node = nodeList.item(i);
				
				if (node.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) node;
					
					String id = eElement.getAttribute("id");
					
					String titol = eElement.getElementsByTagName("titol").item(0).getTextContent();

					String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
					
					String anypubli = eElement.getElementsByTagName("anypubli").item(0).getTextContent();

					String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
					
					String nombrepag = eElement.getElementsByTagName("nombrepag").item(0).getTextContent();
					
					Llibre Lli = new Llibre(id,titol,autor,anypubli,editorial,nombrepag);
					
					Llibres.add(Lli);
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return Llibres;
	}
	
	// Este metode ens permitira recorrer el ArrayList dels llibres i crearne uno nou, que se afegira a eixa mateixa llista
	// i creara un ficher xml nou o sobrescruira el mateix ficher que teniem, amb el llibre nou que se ha afexit.
	public static void writeXMLfile(ArrayList<Llibre> Llibrests) 
	{
		try 
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document document = dBuilder.newDocument();
			Element raiz = document.createElement("llibres");
			document.appendChild(raiz);
		
			
			for (Llibre Lli : Llibrests)
			{
				Element llibre = document.createElement("llibre");
				String id = String.valueOf(Lli.getId());
				llibre.setAttribute("id", id);
				raiz.appendChild(llibre);
					
				Element titol = document.createElement("titol");
				titol.appendChild(document.createTextNode(String.valueOf(Lli.getTitol())));
				llibre.appendChild(titol);
				
				Element autor = document.createElement("autor");
				autor.appendChild(document.createTextNode(String.valueOf(Lli.getAutor())));
				llibre.appendChild(autor);
							
				Element anypubli = document.createElement("anypubli");
				anypubli.appendChild(document.createTextNode(String.valueOf(Lli.getAnyPubli())));
				llibre.appendChild(anypubli);
							
				Element editorial = document.createElement("editorial");
				editorial.appendChild(document.createTextNode(String.valueOf(Lli.getEditorial())));
				llibre.appendChild(editorial);
						
				Element nombrepag = document.createElement("nombrepag");
				nombrepag.appendChild(document.createTextNode(String.valueOf(Lli.getNombrepag())));
				llibre.appendChild(nombrepag);
			}
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer at = tf.newTransformer();
			
			at.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			
			at.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			
			at.setOutputProperty(OutputKeys.INDENT, "yes");	
			
			DOMSource ds = new DOMSource(document);
			
			try 
			{
				FileWriter fw = new FileWriter("llibres.xml");
				StreamResult result = new StreamResult(fw);
				at.transform(ds,result);
				fw.close();
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		} 
		catch(TransformerException ex) 
		{
			System.out.println("Error al escriure el document");
		}
		catch(ParserConfigurationException ex) 
		{
			System.out.println("Error construint el document");
		}
	}
	
	// Este metode ens permitira crear un nou llibre que se afegira al ArrayList del metode recorrerTots i que se
	// creara un ficher xml nou o sobrescruira el mateix ficher que teniem, amb el llibre nou que se ha afexit.
	public static int crearLlibre(Llibre llibre) 
	{
		ArrayList<Llibre> Llibrests = recuperarTots();
		
		Llibrests.add(llibre);
		
		writeXMLfile(Llibrests);
		
		return Integer.parseInt(llibre.getId());
	}
	
	// Este metode ens permitira a traves de un id que nosaltres preguntarem, actualizar un llibre que estaba en el ArrayList del metode recorrerTots i que se
	// creara un ficher xml nou o sobrescruira el mateix ficher que teniem, amb el llibre actualizat de la llista.
	public static void actualitzarLlibre(int id) 
	{
		ArrayList<Llibre> Llibrests = recuperarTots(); 
		
		System.out.print("Cambia el titol: ");
		Llibrests.get(id - 1).titol = teclat.nextLine();
		
		System.out.print("Cambia el autor: ");
		Llibrests.get(id - 1).autor = teclat.nextLine();
		
		System.out.print("Cambia el any de publicacio: ");
		Llibrests.get(id - 1).anypubli = teclat.nextLine();
		
		System.out.print("Cambia la editorial: ");
		Llibrests.get(id - 1).editorial = teclat.nextLine();
		
		System.out.print("Cambia el nombre de pagines: ");
		Llibrests.get(id - 1).nombrepag = teclat.nextLine();
		
		writeXMLfile(Llibrests);
	}
	
	// Este metode ens permitira a traves de un id que nosaltres preguntarem, borrar un llibre que estaba en el ArrayList del metode recorrerTots i que se
	// creara un ficher xml nou o sobrescruira el mateix ficher que teniem, amb el llibre borrar de la llista.
	public static void borrarLlibre(int id) 
	{
		ArrayList<Llibre> Llibrests = recuperarTots();
		
		Llibrests.remove(id - 1);
		
		writeXMLfile(Llibrests);
	}
	
	// En el metode main, tindrem un menu amb varies opcion i depenguent del numero que nosatres posem cuant ens pregunten
	// que numero volem posar, a traves de un switch, fara el metode que nosatres el posat al introduir un numero.
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		System.out.println("Menu de la biblioteca");
		System.out.println("");
		System.out.println("1. Mostrar tots els titols de la biblioteca.");
		System.out.println("2. Mostrar informacion detallada d'un llibre.");
		System.out.println("3. Crear nou llibre.");
		System.out.println("4. Actualitzar llibre.");
		System.out.println("5. Borrar llibre.");
		System.out.println("6. Tanca la biblioteca.");	
		System.out.println("");
		
		System.out.print("Introduix un numero: ");
		int numero;
		numero = Integer.parseInt(teclat.nextLine());
		
		String id;
		String titol;
		String autor;
		String anypubli;
		String editorial;
		String nombrepag;
		
		switch(numero) 
		{
			case 1:
				ArrayList<Llibre> Llibrests = recuperarTots();
				
				for(Llibre lli : Llibrests) 
				{
					mostrarLlibre(lli);
				}
				break;
			case 2:
				System.out.print("Introduix un id: ");
				int identifi = Integer.parseInt(teclat.nextLine());
				recuperarLlibre(identifi);
				break;
			case 3:
				System.out.print("Introduix un id: ");
				id = teclat.nextLine();
				
				System.out.print("Introduix un titol: ");
				titol = teclat.nextLine();
				
				System.out.print("Introduix un autor: ");
				autor = teclat.nextLine();
				
				System.out.print("Introduix un any de publicacio: ");
				anypubli = teclat.nextLine();
				
				System.out.print("Introduix una editorial: ");
				editorial = teclat.nextLine();
				
				System.out.print("Introduix un nombre de pagines: ");
				nombrepag = teclat.nextLine();
				
				Llibre nou = new Llibre(id,titol,autor,anypubli,editorial,nombrepag);
				
				crearLlibre(nou);
				break;
			case 4:
				System.out.print("Introduix un id: ");
				int identifica = Integer.parseInt(teclat.nextLine());
				actualitzarLlibre(identifica);
				break;
			case 5:
				System.out.print("Introduix un id: ");
				int identificaor = Integer.parseInt(teclat.nextLine());
				borrarLlibre(identificaor);
				break;
			case 6:
				System.out.print("Tanca la biblioteca");
				break;
			default:
				System.out.print("El nombre introduit no es correcte");
				break;
		}
	}
}