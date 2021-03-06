package org.pmp.budgeto.view.account

import akka.actor.{ActorRef, Props}
import akka.pattern.ask
import org.pmp.budgeto.EventuateContext
import org.pmp.budgeto.common.CommandFailure
import org.pmp.budgeto.domain.account.{AccountActor, _}
import org.scalatest.Inspectors._
import org.scalatest.Matchers._

class AccountActorViewTest extends EventuateContext {

//  var accountActor: ActorRef = null
//  var accountViewActor: ActorRef = null
//  var accountId1: String = null
//  var accountId2: String = null
//
//  override def setup = {
//    accountActor = system.actorOf(Props(new AccountActor(actorId("AccountActor"), eventLog)))
//    accountViewActor = system.actorOf(Props(new AccountViewActor(actorId("AccountActor"), eventLog)))
//
//    val CommandSuccess(AccountCreated(Account(resAccountId1, _, _, _, _))) = waitFor(accountActor ? CreateAccount("testAccount", "a note", 125))
//    accountId1 = resAccountId1
//    waitFor(accountActor ? CreateOperation(accountId1, "ope 1", 150))
//    waitFor(accountActor ? CreateOperation(accountId1, "ope 2", 150))
//    waitFor(accountActor ? CreateOperation(accountId1, "ope 3", -20))
//
//    val CommandSuccess(AccountCreated(Account(resAccountId2, _, _, _, _))) = waitFor(accountActor ? CreateAccount("testAccount2", "a note2", 2125))
//    accountId2 = resAccountId2
//    waitFor(accountActor ? CreateOperation(accountId2, "ope x", 10000))
//    waitFor(accountActor ? CloseAccount(resAccountId2))
//
//    waitFor(accountActor ? CreateAccount("testAccount3", "bzzzzzz"))
//  }
//
//  test("ask to list active account") {
//    Given("an account actor, and an account view actor, and 3 accounts created but 1 closed")
//
//    When("send a print command")
//    val future = accountViewActor.actorRef ? PrintAccounts()
//
//    Then("I expected to have a list of 2 accounts and empty for closed accounts")
//    val PrintAccountsSuccess(accounts) = waitFor(future)
//    accounts should have size 2
//    forAll(accounts) { x => x should matchPattern {
//      case Account("testAccount", "a note", 125, 405, _) =>
//      case Account("testAccount3", "bzzzzzz", 0, 0, _) =>
//    }
//    }
//  }
//
//  test("ask to list all closed account") {
//    Given("an account actor, and an account view actor, and 3 accounts created but 1 closed")
//
//    When("send a print closed command")
//    val future = accountViewActor.actorRef ? PrintClosedAccounts()
//
//    Then("I expected to have a list of 2 accounts and empty for closed accounts")
//    val PrintClosedAccountsSuccess(accounts) = waitFor(future)
//    accounts should have size 1
//    forAll(accounts) { x => x should matchPattern {
//      case Account("testAccount2", "a note2", 2125, 12125, _) =>
//    }
//    }
//  }
//
//  test("ask to get account information on account that not exist") {
//    Given("an account actor, and an account view actor, and 3 accounts created but 1 closed")
//
//    When("send a print acount for if not exist")
//    val future = accountViewActor.actorRef ? PrintAccount("125")
//
//    Then("I expected to have a failure")
//    val CommandFailure(message, _) = waitFor(future)
//    message should be( """account with id "125" not exist""")
//  }
//
//  test("ask to list an account 1 that is active") {
//    Given("an account actor, and an account view actor, and 3 accounts created but 1 closed")
//
//    When("send a print account command")
//    val future = accountViewActor.actorRef ? PrintAccount(accountId1)
//
//    Then("I expected to get account 1")
//    val PrintAccountSuccess(account) = waitFor(future)
//    account should matchPattern {
//      case Account("testAccount", "a note", 125, _, _) =>
//    }
//  }
//
//  test("ask to get an account that is not active") {
//    Given("an account actor, and an account view actor, and 3 accounts created but 1 closed")
//
//    When("send a print account command")
//    val future = accountViewActor.actorRef ? PrintAccount(accountId2)
//
//    Then("I expected to have the account")
//    val PrintAccountSuccess(account) = waitFor(future)
//    account should matchPattern {
//      case Account("testAccount2", "a note2", 2125, _, _) =>
//    }
//  }
//
//  test("ask to list operations on account that not exist") {
//    Given("an account actor, and an account view actor, and 3 accounts created but 1 closed")
//
//    When("send a print closed command")
//    val future = accountViewActor.actorRef ? PrintAccountOperations("125")
//
//    Then("I expected a failure")
//    val CommandFailure(message, _) = waitFor(future)
//    message should be( """account with id "125" not exist""")
//  }
//
//  test("ask to list operations on account 1 that is active") {
//    Given("an account actor, and an account view actor, and 3 accounts created but 1 closed")
//
//    When("send a print closed command")
//    val future = accountViewActor.actorRef ? PrintAccountOperations(accountId1)
//
//    Then("I expected to have a list of 3 operations in the account")
//    val PrintAccountOperationsSuccess(operations) = waitFor(future)
//    operations should have size 3
//    forAll(operations) { x => x should matchPattern {
//      case AccountOperation(_, "ope 1", 150, _) =>
//      case AccountOperation(_, "ope 2", 150, _) =>
//      case AccountOperation(_, "ope 3", -20, _) =>
//    }
//    }
//  }
//
//  test("ask to list operations on account 2 that is not active") {
//    Given("an account actor, and an account view actor, and 3 accounts created but 1 closed")
//
//    When("send a print closed command")
//    val future = accountViewActor.actorRef ? PrintAccountOperations(accountId2)
//
//    Then("I expected to have a list of 3 operations in the account")
//    val PrintAccountOperationsSuccess(operations) = waitFor(future)
//    operations should have size 1
//    forAll(operations) { x => x should matchPattern {
//      case AccountOperation(_, "ope x", 10000, _) =>
//    }
//    }
//  }

}