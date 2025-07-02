public class AdminLogic {

	public static void processAdminOptions() {
		boolean ifAdminContinue = true;

		while(ifAdminContinue) {
			int adminChoice = UserInterface.displayAdminScreen();

			switch(adminChoice) {
				case 1 -> processAddBook();
				case 2 -> processUpdateBook();
				case 3 -> processDeleteBook();
				case 0 -> ifAdminContinue = false;
			}
		}
	}

	private static void processAddBook() {
		System.out.println("Add Book");
	}

	private static void processUpdateBook() {
		System.out.println("Update Book");
	}

	private static void processDeleteBook() {
		System.out.println("Delete book");
	}

}
