// Nama    : EPAN ALAMBANA
// NIM     : 23090005
// Kelas   : A
// Semester: 4

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

public class P_23090005_A_2025 {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "kampus";
    private static final String COLLECTION_NAME = "mahasiswa";

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create(CONNECTION_STRING)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

            // CREATE
            Document mhs = new Document("nama", "Epan Alambana")
                    .append("nim", "23090005")
                    .append("kelas", "A")
                    .append("semester", 4);
            collection.insertOne(mhs);
            System.out.println("Data mahasiswa berhasil ditambahkan: " + mhs.toJson());

            // READ
            System.out.println("\nData Mahasiswa:");
            FindIterable<Document> allMahasiswa = collection.find();
            for (Document doc : allMahasiswa) {
                System.out.println(doc.toJson());
            }

            // UPDATE
            Bson filter = Filters.eq("nim", "23090005");
            Bson update = new Document("$set", new Document("semester", 5));
            collection.updateOne(filter, update);
            System.out.println("\nData mahasiswa dengan NIM 23090005 telah diupdate ke semester 5.");

            // DELETE
            collection.deleteOne(Filters.eq("nama", "Epan Alambana"));
            System.out.println("\nData mahasiswa atas nama Epan Alambana telah dihapus.");

            // SEARCH
            Document cari = collection.find(Filters.eq("kelas", "A")).first();
            if (cari != null) {
                System.out.println("\nHasil pencarian mahasiswa di kelas A: " + cari.toJson());
            } else {
                System.out.println("\nTidak ada mahasiswa ditemukan di kelas A.");
            }
        }
    }
}
