package testModele;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestArbitre.class, TestConnexion.class, TestEcurie.class, TestEquipe.class,
		TestGestionnaireEsporter.class, TestJeu.class, TestJoueur.class, TestPoule.class, TestResponsable.class,
		TestTournoi.class, TestUtilisateur.class })
public class AllTests {

}
