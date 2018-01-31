package com.threepio.testing

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.layout_contact_item.view.*

class ContactsActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_contacts)

    val contacts = listOf<Contact>(
      Contact(name = "Ace Ventura", status= "The pet detective", isOnline = true),
      Contact(name = "Jack Sparrow", status= "About to go on a new adventure", isOnline = false),
      Contact(name = "Darth Vader", status= "Not feeling like going anywhere", isOnline = true))

    contacts_view.layoutManager = LinearLayoutManager(this)
    contacts_view.adapter = ContactsAdapter(contacts)
  }
}

data class Contact(val name: String, val status: String, val isOnline: Boolean)

class ContactsAdapter(private val contacts: List<Contact>): RecyclerView.Adapter<ContactHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.layout_contact_item, parent, false)
    return ContactHolder(view)
  }

  override fun getItemCount(): Int {
    return contacts.size
  }

  override fun onBindViewHolder(holder: ContactHolder, position: Int) {
    holder.bind(contacts[position])
  }
}

class ContactHolder(val view: View): RecyclerView.ViewHolder(view) {

  fun bind(contact: Contact) {
    view.name.text = contact.name
    view.status.text = contact.status
    view.online.visibility = if(contact.isOnline) View.VISIBLE else View.GONE
  }
}