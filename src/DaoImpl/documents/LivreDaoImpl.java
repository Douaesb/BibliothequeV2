package DaoImpl.documents;

import Dao.DocumentDao;

public abstract class LivreDaoImpl implements DocumentDao {
    public abstract void addDocument();
    public abstract void editDocument();
    public abstract void displayDocument();
    public abstract void displatAllDocuments();
    public abstract void deleteDocument();

    public abstract void searchDocument();
}
