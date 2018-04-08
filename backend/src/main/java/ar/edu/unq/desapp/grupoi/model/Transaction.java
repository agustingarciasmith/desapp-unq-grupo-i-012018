package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.InvalidTransaction;

public class Transaction {
    private final Publication publication;
    private User client;

    public Transaction(Publication publication, User client) {
        if (client.equals(publication.getOwner())) throw new InvalidTransaction();
        this.client = client;
        this.publication = publication;
    }

    public User getClient() {
        return client;
    }

    public Publication getPublication() {
        return publication;
    }
}
