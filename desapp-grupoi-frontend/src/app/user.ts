export class User {
  static emptyUser() {
    return new User(null, null, null, null, null);
  }

  constructor(
    private id: Number,
    private name: String,
    private email: String,
    private address: String,
    private cuil: String) {}
}
