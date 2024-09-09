package DaoImpl.documents;

import Dao.Document;

public abstract class Livre implements Document {
    public abstract void addDocument();
    public abstract void editDocument();
    public abstract void displayDocument();
    public abstract void displatAllDocuments();
    public abstract void deleteDocument();

    public abstract void searchDocument();
}
