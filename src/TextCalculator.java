
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import java.math.*;
import java.text.SimpleDateFormat;

public class TextCalculator extends JFrame implements ActionListener{

    String[] str= {"CE","계산","+","-","x","/","%","e","L","b"};
    JTextField tf1,tf2;
    String numStr="";
    JButton b2=new JButton();
    FileWriter fout;
    BufferedWriter bufout;
    PrintWriter pw;

    public TextCalculator() {
        setTitle("기록 계산기");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c=getContentPane();

        JPanel pa=new JPanel();
        JPanel pb=new JPanel();
        JPanel pc=new JPanel();


        pa.setBackground(Color.LIGHT_GRAY);
        JLabel label1=new JLabel("수식입력");
        tf1=new JTextField(25);
        b2.setText("파일기록");
        b2.setBackground(Color.WHITE);

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                SimpleDateFormat format = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");					Date time = new Date();

                String datas=tf1.getText();
                String datas2=tf2.getText();
                String time1 = format.format(time);
                String datass= time1+":   "+datas+" ="+datas2;


                try {
                    fout = new FileWriter("C:\\Users\\윤석효\\Desktop\\texts.txt",true);
                    bufout=new BufferedWriter(fout);
                    pw=new PrintWriter(bufout,true);

                    pw.write(datass+" \n");
                } catch (IOException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }

                finally{
                    pw.close();
                }



            }
        });

        pa.add(b2);
        pa.add(label1);
        pa.add(tf1);


        pb.setBackground(Color.LIGHT_GRAY);
        JLabel label2=new JLabel("계산결과");
        tf2=new JTextField(25);


        pb.add(label2);
        pb.add(tf2);


        pc.setLayout(new GridLayout(5,4,5,5));


        for(int i=0;i<20;i++)
        {
            JButton b=new JButton();

            if(i<10) {
                b.setText(i+"");
                b.setBackground(Color.WHITE);
            }

            else {
                b.setText(str[i-10]);
                b.setBackground(Color.WHITE);
            }
            if(i>11)
                b.setBackground(Color.WHITE);
            pc.add(b);
            b.addActionListener(this);

        }



        c.add(pc);
        c.add(pa,BorderLayout.NORTH);
        c.add(pb,BorderLayout.SOUTH);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str=e.getActionCommand();

        if(str.equals("CE")) {
            numStr="";
            tf1.setText(numStr);
            tf2.setText("");
        }



        else if(str.equals("계산")) {

            StringTokenizer st=new StringTokenizer(numStr,"+-x/%eL",true);
            int num1=Integer.parseInt(st.nextToken());
            String op=st.nextToken();
            int num2=Integer.parseInt(st.nextToken());
            int num3=1;
            double num4=0;

            switch(op) {
                case "+":tf2.setText(num1+num2+""); break;
                case "-":tf2.setText(num1-num2+""); break;
                case "x":tf2.setText(num1*num2+""); break;
                case "/":tf2.setText(num1/num2+""); break;
                case "%":tf2.setText(num1%num2+""); break;
                case "e":{
                    for(int i=0;i<num2;i++) {
                        num3*=num1;
                    }

                    tf2.setText(num3+"");
                    break;
                }

                case "L":{
                    for(int i=0;i<num2;i++) {
                        num4=Math.sqrt(num1);
                    }
                    tf2.setText(num4+"");
                    break;
                }

                default: tf2.setText("오류"); break;
            }

            //numStr="";
            //tf1.setText(numStr);
        }

        else if(str.equals("b")) {
            int num4=Integer.parseInt(numStr);
            String bin=Integer.toBinaryString(num4);

            tf2.setText(bin);
        }
        else {
            tf1.setText("");
            numStr+=str;
            tf1.setText(numStr);
        }
    }

    public static void main(String[] args) {

        new TextCalculator();
    }
}