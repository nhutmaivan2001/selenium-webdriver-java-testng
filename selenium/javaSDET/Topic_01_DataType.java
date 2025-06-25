package javaSDET;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.*;

public class Topic_01_DataType {
    //2 nhóm kiểu dữ liệu

    //cách khai báo
    //Access Modifier: phạm vi truy câpj (Private/ public/ protected/ default)
    // 1- Access Modifier - Kiểu dữ liệu -Tên biến - Giá trị của biến ( Ngoài hàm/ trong hàm đều được)

    public char cName = 'b';

    // 2.1 - Access Modifier - Kiểu dưx liệu - Tên biến

    private  char cAddress;

    // 2.2 _- Tên biến - Giá trị gán sau (Hàm)

    public void clickToElement(){
        cAddress = 'c';
    }

    //Nhóm 1 - Kiểu dữ liệu nguyên thủy (primitive type)
    // char: kí tự (character)
    // Khi gán giá trị (khởi tạo) thì không nằm trong dấu nháy đơn

    char cZip = 'b';

    //byte/ short/ int/ long: số nguyên
    // Khi gán giá trị (khởi tạo) thì không nằm trong dấu gì

    byte bNumber = -120;

    short sNumber = 1200;

    int iNumber = 35000;

    long lNumber = 1288888;

    // float/ double: số thực
    // Khi gán giá trị (khởi tạo) thì không nằm trong dấu gì

    float fNumber = 15.4f;

    double dNumber = 15.354d;

    boolean gender = true;

    // Nhóm 2 - Kiểu tham chiếu (reference type/ non-primitive)
    // String: Chuỗi kí tự
    // Khi gán giá trị (khởi tạo) thì nằm trong dấu nháy đôi ""

    String fullName = "Mai Van Nhut";

    // Class

    FirefoxDriver fDriver = new FirefoxDriver();

    Actions actions = new Actions(fDriver);

    Topic_01_DataType topic01 = new Topic_01_DataType();

    // Interface

    WebDriver driver;
    JavascriptException jsException;

    // Array

    String[] studentName = {"Nhựt", "Hạ"};

    Integer[] studentPhone = {400060, 4389849, 4934395};

    // List/ Set/ Quêu

    List<String> studentAddress = new ArrayList<String>();

    List<String> studentCity = new LinkedList<String>();

    // Map

    Map<String, Integer> zip = new HashMap<String, Integer>();
    // Object

    Object name = "Nam";

    Object phone = 1245;

    Object isDisplayed = true;
    // Convention: Quy ước khi lập trình
    // Tên biến/ tên hàm: viết dưới dạng camel case
    // Chữ cái đầu tiên luôn viết thường, các chữ cái tiếp theo viết hoa kí tự đầu tiên
    // name/ address/ zipCpde/ getUserName,..

}
