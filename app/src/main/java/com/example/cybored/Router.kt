package com.example.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.example.myapplication.categories.CategoriesFragment
import com.example.myapplication.events.EventsFragment
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplication.model.CategoryModel


class Router {

  companion object {
      val BOLT_NAME = "ee.mtakso.client"
      var activity: AppCompatActivity? = null
      var fragmentManager: FragmentManager? = null
      var packageManager: PackageManager? = null

      fun start(activity: AppCompatActivity) {
          this.activity = activity
          fragmentManager = activity.supportFragmentManager
          packageManager = activity.getPackageManager()
      }

      fun stop() {
          this.activity = null
          fragmentManager = null
          packageManager = null
      }

      fun openCategories() {
          val newFragment = CategoriesFragment()
          fragmentManager?.beginTransaction()
                  ?.replace(R.id.container, newFragment)
                  ?.addToBackStack(newFragment.javaClass.name)
                  ?.commit()
      }

      fun openEvents(category: CategoryModel) {
          val newFragment = EventsFragment().withCategory(category.id)
          fragmentManager?.beginTransaction()
                  ?.replace(R.id.container, newFragment)
                  ?.addToBackStack(newFragment.javaClass.name)
                  ?.commit()
          activity?.let {
              it.supportActionBar?.title = category.title
          }
      }

      fun openLink(url:String?) {
          url ?: return
          activity?.let {
              startActivity(it, Intent(Intent.ACTION_VIEW, Uri.parse(url)), Bundle())
          }
      }

      fun openRoute(coord: List<Double>) {
          val gmmIntentUri = Uri.parse("google.navigation:q=" + coord[0] + "," + coord[1])
          val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
          mapIntent.setPackage("com.google.android.apps.maps")
          activity?.let {
              startActivity(it, mapIntent, Bundle())
          }
      }

      fun openBolt() {
          val launchIntent = packageManager?.getLaunchIntentForPackage(BOLT_NAME)
          if (launchIntent == null) {
              try {
                  activity?.let {
                      startActivity(it, Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$BOLT_NAME")), Bundle())
                  }
              } catch (anfe: android.content.ActivityNotFoundException) {
                  activity?.let {
                      startActivity(it, Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$BOLT_NAME")), Bundle())
                  }
              }
              return
          } else {
              activity?.let {
                  ContextCompat.startActivity(it, launchIntent, Bundle())//null pointer check in case package name was not found
              }
          }
      }

  }

}