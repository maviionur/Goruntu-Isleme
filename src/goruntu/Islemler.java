
package goruntu;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class Islemler 
{
    
 
    
    
   int [][] bits;
	
	public BufferedImage dosyaOku(String path)
	{
		
		File file ;
		BufferedImage img = null ;
		try
		{
			
			file = new File(path);
			img = ImageIO.read(file);
			
		}
		catch(Exception e)
		{
			System.out.println("Sıkıntı var");
		}
		
		
		
		return img;
	}
	

	public void dosyaYaz(String path,BufferedImage image)
	{
		File f = null;
		BufferedImage img = null;
		
		try {
			f = new File(path);
			ImageIO.write(image, "jpg", f);
		} catch (Exception e) {
			System.out.println("Yazamıyom");
		}
		
		
		
	}
	
	public int[][] bitOku() {
		
		
		BufferedImage o = dosyaOku("C:\\Users\\USER\\Desktop\\ONUR\\Goruntu\\Java\\eskiyazi.jpg");//   String path yap 
		bits = new int[o.getWidth()][o.getHeight()];
                
		
	
		Color color;
		
		for(int y=0;y<o.getHeight();y++)
			for(int x=0;x<o.getWidth();x++)
			{
				int s = 0;
				s= o.getRGB(x, y);
				color= new Color(s);
				if(color.getBlue() == 0)
				{
					bits[x][y] = 1;
				}
				else
				{
					bits[x][y] = 0;
				}
			}
		 return bits;
		
		}
	
	
	public void hideImage()
	{
		BufferedImage o = dosyaOku("C:\\Users\\USER\\Desktop\\ONUR\\Goruntu\\Java\\araba.jpg");
		int p;
		
		int [][]gomulecek = new int[o.getWidth()][o.getHeight()];
		gomulecek = bitOku();
		for(int x=0;x<gomulecek.length;x++){
			for(int y=0;y<gomulecek[x].length;y++)
			{
			 p=o.getRGB(x, y); // hata burda olabilir.Dışarda resim okunup değerler ona göre ayarlanabilir.
			 
				if(gomulecek[x][y]==0) 
				{
					p &= ~(1<<0);
				}
				else 
				{
					p |= (1<<0);
					
				}
				
				o.setRGB(x, y, p);
				
                        }	
			}
		
		dosyaYaz("C:\\Users\\USER\\Desktop\\ONUR\\Goruntu\\Java\\gomulu.jpg",o);
		
	}
        
        public void erosion(){
            
            int row = 3;
            int column = 0;
            int strElement[][] = new int[1][3];
            for(int i=0; i< row; i++){
                strElement[column][i] = 1;
                }
            
            BufferedImage o = dosyaOku("C:\\Users\\USER\\Desktop\\araba.jpg");
            int [][]mainImage = new int[o.getWidth()][o.getHeight()];
            mainImage = bitOku();
	    int [][]resim = new int[1][3];
            resim = strElement;
            int erodedImage[][] = new int[o.getWidth()][o.getHeight()];
            
            
            for(int j=1;j<mainImage.length-2;j++){
                for(int k=0;k<mainImage[j].length;k++){
                    
                    if((mainImage[j-1][k] == 1) && (mainImage[j][k] == 1) && (mainImage[j+1][k] == 1)){
                        erodedImage[j][k] = 1;
                    }else{
                        erodedImage[j][k] = 0;
                    }
             
                }
                for(int a=0; a<o.getWidth();a++){
                    erodedImage[0][a] = 0;
                }
                for(int b=0; b<o.getWidth();b++){
                    erodedImage[o.getHeight()-1][b] = 0;
                }
                
            }
            
            
            
        }
        
        
        
        
        
        
        
        
        
        
        
          // Best dilate by one solution
        int [][]dilate(){
            
                int[][] image = bitOku(); 
             for (int i=1; i<image.length-1; i++){
                 for (int j=1; j<image[i].length-1; j++){
                     
                     if (image[i][j] == 1){
                         image[i-1][j] = 1; // ust
                         image[i-1][j-1] = 1; // ust
                         image[i-1][j+1] = 1; // ust
                         image[i+1][j-1] = 1; // alt
                         image[i+1][j] = 1; // alt
                         image[i+1][j+1] = 1; // alt
                         image[i][j-1] = 1; // sol 
                         image[i][j+1] = 1; // sağ
                         image[i][j] = 1; // orta
                     }
                    
                 }
             }
             
             
             
             
             convertImage();
              
             return image;
        }
        
        
        
        
        
        public void convertImage(){
           int [][]o = bitOku();
           File f = null;
           BufferedImage a = dosyaOku("C:\\Users\\USER\\Desktop\\ONUR\\Goruntu\\Java\\eskiyazi.jpg");
                        BufferedImage img = new BufferedImage(
                o.length, o[0].length, BufferedImage.TYPE_BYTE_GRAY);  
            for(int x = 0; x < o.length; x++){
                for(int y = 0; y<o[x].length; y++){
                    img.setRGB(x, y, (int)Math.round(o[x][y]));
                }
            }
           dosyaYaz("C:\\Users\\USER\\Desktop\\ONUR\\Goruntu\\Java\\dilation.jpg",img);

                    }
        
    
}
