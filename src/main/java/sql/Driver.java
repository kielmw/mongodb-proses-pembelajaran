//package sql;
//
//import com.tugasakhir.mongodbprosespembelajaran.model.Member;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//@Component
//public class Driver {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @PostConstruct
//    public void init() {
//        // Listener untuk mendengarkan perubahan pada tabel "Student" di MySQL
//        jdbcTemplate.query("SELECT * FROM kontrol_kelas.students", (rs, rowNum) -> {
//            String id = rs.getLong("id");
//            String nim = rs.getString("nim");
//            String nama = rs.getString("nama");
//            String idKelas = rs.getString("kelas_id");
//
//            // Lakukan transformasi data jika diperlukan
//            // Misalnya, jika Anda memiliki model "Member" di MongoDB, sesuaikan transformasi sesuai kebutuhan Anda
//            Member member = new Member(id, nim, nama, idKelas);
//
//            // Simpan data ke MongoDB
//            mongoTemplate.save(member);
//            return null;
//        });
//    }
//    @Scheduled(fixedRate = 10000) // Run every 10 seconds
//    public void processMembersForAllKelas() {
//        // Retrieve distinct idKelas values from MongoDB
//        List<String> distinctIdKelas = mongoTemplate.getCollection("member_collection")
//                .distinct("idKelas", String.class)
//                .into(new ArrayList<>());
//
//        // Process members for each idKelas
//        for (String idKelas : distinctIdKelas) {
//            addMember(idKelas, "member_name" , "nim");
//            List<Member> members = getMembersByKelasId(idKelas);
//            // Process retrieved members as needed
//        }
//    }
//    public void addMember(String idKelas, String nama, String nim) {
//        Member member = new Member();
//        member.setIdKelas(idKelas);
//        member.setNama(nama);
//        member.setNim(nim);
//        // Set other properties as needed
//
//        mongoTemplate.save(member);
//    }
//    public List<Member> getMembersByKelasId(String idKelas) {
//        Query query = new Query(Criteria.where("idKelas").is(idKelas));
//        return mongoTemplate.find(query, Member.class);
//    }
//}