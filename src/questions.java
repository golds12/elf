import org.json.simple.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import tools.*;

@WebServlet(name = "questions", urlPatterns = "/questions")
public class questions extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //HttpSession session = request.getSession();
        response.setContentType("text/json; charset=utf-8");

        int id = myTools.string2int((String) request.getParameter("id"));
        int testId = myTools.string2int((String) request.getParameter("testid"));


        JSONArray questions = getQuestion(id,testId);
        //String jsonText = "[{\"body\":\"Która litera jest samogloska?\",\"answers\":[{\"body\":\"A\",\"isTrue\":false},{\"body\":\"B\",\"isTrue\":false},{\"body\":\"C\",\"isTrue\":false},{\"body\":\"D\",\"isTrue\":false},{\"body\":\"E\",\"isTrue\":true},{\"body\":\"F\",\"isTrue\":false},{\"body\":\"G\",\"isTrue\":false},{\"body\":\"H\",\"isTrue\":false},{\"body\":\"I\",\"isTrue\":false}]},{\"body\":\"Która liczba jest parzysta?\",\"answers\":[{\"body\":\"1\",\"isTrue\":false},{\"body\":\"2\",\"isTrue\":true},{\"body\":\"3\",\"isTrue\":false},{\"body\":\"4\",\"isTrue\":true},{\"body\":\"5\",\"isTrue\":false},{\"body\":\"6\",\"isTrue\":true}]}]";
        //int userId = (int) session.getAttribute("user");
        PrintWriter out = response.getWriter();
        out.println(myTools.shuffleJsonArray(questions));
        //out.println(makeQuestionsArray(questions).toJSONString());
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private JSONArray getQuestion(int id, int testId){
        String sql = "SELECT `ID`, `test_id`, `contents` FROM `questions`";
        if (id > 0) sql += " where id = " + id;
        else if (testId > 0) sql += " where test_id = " + testId;

        return DB.getSqlToJSON(sql);
    }
//
//    private JSONArray jsonToArray(String jsonText){
//        JSONArray json = new JSONArray();
//        JSONParser jsonParser = new JSONParser();
//
//        if ((jsonText != null) && !(jsonText.isEmpty())){
//            try {
//                json = (JSONArray) jsonParser.parse(jsonText);
//            }
//            catch ( org.json.simple.parser.ParseException e) {
//                e.printStackTrace();
//                json = new JSONArray();
//            }
//        }
//
//        return json;
//    }
//
//    private JSONObject jsonToObject(String jsonText){
//        JSONObject json = new JSONObject();
//        JSONParser jsonParser = new JSONParser();
//
//        if ((jsonText != null) && !(jsonText.isEmpty())){
//            try {
//                json = (JSONObject) jsonParser.parse(jsonText);
//            }
//            catch ( org.json.simple.parser.ParseException e) {
//                e.printStackTrace();
//                json = new JSONObject();
//            }
//        }
//
//        return json;
//    }
//
//    private List<Integer> draw4int(int maxSize){
//        Random generator = new Random();
//
//        List<Integer> integers;
//
//        integers = new LinkedList<Integer>();
//        for (int i = 1; i < maxSize + 1; i++) {
//            integers.add(i);
//        }
//
//        List<Integer> readyInt;
//        readyInt = new LinkedList<Integer>();
//        for (int i = 0; i < 4; i++){
//            int j = generator.nextInt(maxSize-1);
//            readyInt.add(integers.get(j));
//            //System.out.print("\nWylosowałem: "+integers.get(j)+",");
//            integers.remove(j);
//            maxSize = integers.size();
//        }
////
////        System.out.print("\nLiczby wylosowane:");
////        for(int k = 0; k < readyInt.size(); k++){
////            System.out.print(readyInt.get(k)+",");
////        }
//
//        return readyInt;
//    }
//
//    private JSONArray draw4answers(JSONArray answers) {
//        JSONArray readyAnswers = new JSONArray();
//        if (answers.size() < 4) {System.out.println("Pytanie nie ma conajmniej 4 odpowiedzi"); return readyAnswers;}
//
//        List<Integer> readyInt = draw4int(answers.size());
//        for (int j = 0; j < 4; j++) {
//            readyAnswers.add(answers.get(readyInt.get(j)));
//        }
//        return readyAnswers;
//    }
//
//    private boolean isAtleastOneTrue(JSONArray answers){
//        for (int i = 0; i < answers.size(); i++){
//            //System.out.println(((JSONObject)answers.get(i)).get("isTrue"));
//            if (((JSONObject)answers.get(i)).get("isTrue").toString().equals("true"))  return true;
//        }
//        return false;
//    }
//
//    private JSONArray makeQuestionsArray(JSONArray questions){
//        JSONArray readyQuestions = new JSONArray();
//
//        for (int i = 0; i < questions.size(); i++){
//            JSONObject question = (JSONObject) questions.get(i);
//            JSONObject readyQuestion = new JSONObject();
//
//            JSONArray answers = (JSONArray) question.get("answers");
//            readyQuestion.put("body", question.get("body"));
//            JSONArray readyAnswers;
//
//            do{
//                readyAnswers = draw4answers(answers);
//                //System.out.println(isAtleastOneTrue(readyAnswers));
//            }while(!isAtleastOneTrue(readyAnswers));
//
//            readyQuestion.put("answers",readyAnswers);
//            readyQuestions.add(readyQuestion);
//        }
//
//        return readyQuestions;
//    }
}


