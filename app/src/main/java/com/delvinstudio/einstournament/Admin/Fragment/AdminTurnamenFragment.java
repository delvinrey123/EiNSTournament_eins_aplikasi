package com.delvinstudio.einstournament.Admin.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.delvinstudio.einstournament.Admin.AdminAddNewTurnamen;
import com.delvinstudio.einstournament.Common.Common;
import com.delvinstudio.einstournament.Interface.ItemClickListener;
import com.delvinstudio.einstournament.Model.ModelJenisTurnamenMl;
import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.ViewHolder.AddJenisTurnamenViewHolder;
import com.delvinstudio.einstournament.ViewHolder.JenisTurnamenViewHolder;
import com.delvinstudio.einstournament.activity.UserListTurnamen.UserListTurnamenDetail;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.squareup.picasso.Picasso;

import java.util.Objects;
import java.util.UUID;

import info.hoang8f.widget.FButton;

import static android.app.Activity.RESULT_OK;

public class AdminTurnamenFragment extends AppCompatDialogFragment {

    FloatingActionButton fabAdd;

    RecyclerView recyclerViewJenisTurnamen;

    //material edit text dan button menu add turnamen
    Button btnSelect, btnUpload;
    MaterialEditText addNamaTurnamen, addDeskripsiTurnamen, addTanggalTurnamen,
            addHargaTurnamen, addNomorWhatsApp, addAuthorTurnamen, addInstagramTurnamen;

    //firebae
    FirebaseStorage storage;
    StorageReference storageReference;

    RelativeLayout rootLayout;

    FirebaseRecyclerOptions<ModelJenisTurnamenMl> options;
    FirebaseRecyclerAdapter<ModelJenisTurnamenMl, AddJenisTurnamenViewHolder> adapter;
    DatabaseReference DataRef;

    ModelJenisTurnamenMl newTurnamen;
    Uri saveUri;

    LayoutInflater inflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_turnamen, container, false);

        //fab add
        fabAdd = (FloatingActionButton) view.findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityTurnamen = new Intent(getActivity(), AdminAddNewTurnamen.class);

                startActivity(activityTurnamen);
                //showDialogAdd();
            }
        });

        //firebase storage
        storage = FirebaseStorage.getInstance();
        //lokasi upload gambar ke firebase storage
        storageReference = storage.getReference();

        rootLayout = (RelativeLayout) view.findViewById(R.id.rootLayout);

        //firebase database recycler
        DataRef = FirebaseDatabase.getInstance().getReference().child("listTurnamen");
        recyclerViewJenisTurnamen = view.findViewById(R.id.recycler_view_list_turnamen);
        recyclerViewJenisTurnamen.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewJenisTurnamen.setHasFixedSize(true);

        loadData();

        return view;
    }

    private void showDialogAdd() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Tambahkan Turnamen Baru");
        alertDialog.setMessage("Tolong isi data dengan lengkap");

        inflater = getLayoutInflater();
        View add_menu_new_turnamen = inflater.inflate(R.layout.add_menu_new_turnamen, null);

        //panggil variabel edittext di menu item
        addNamaTurnamen = add_menu_new_turnamen.findViewById(R.id.et_nama_turnamen);
        addDeskripsiTurnamen = add_menu_new_turnamen.findViewById(R.id.et_deskripsi_turnamen);
        addTanggalTurnamen = add_menu_new_turnamen.findViewById(R.id.et_tanggal_turnamen);
        addHargaTurnamen = add_menu_new_turnamen.findViewById(R.id.et_harga_turnamen);
        addNomorWhatsApp = add_menu_new_turnamen.findViewById(R.id.et_no_wa);
        addAuthorTurnamen = add_menu_new_turnamen.findViewById(R.id.et_author_turnamen);
        addInstagramTurnamen = add_menu_new_turnamen.findViewById(R.id.et_ig_turnamen);

        alertDialog.setView(add_menu_new_turnamen);
        alertDialog.setIcon(R.drawable.ic_turnamen);

        //set button alert dialog
        alertDialog.setPositiveButton("IYA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //here just create a new list turnamen
                if (newTurnamen != null) {
                    DataRef.push().setValue(newTurnamen);
                    Snackbar.make(rootLayout, "New Turnamen" + newTurnamen.getNamaTurnamen() + "Berhasil Ditambahkan", Snackbar.LENGTH_SHORT)
                            .show();
                }

                dialogInterface.dismiss();

            }
        });
        alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Balik ke menu awal lagi ya", Toast.LENGTH_SHORT).show();
            }
        });

        //panggil variabel Button di menu item
        btnSelect = add_menu_new_turnamen.findViewById(R.id.btn_select);
        btnUpload = add_menu_new_turnamen.findViewById(R.id.btn_upload);

        //event onclick button upload
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage(); //untuk memilih gmbar dari galeri
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    private void uploadImage() {
        if (saveUri != null) {
            final ProgressDialog mDialog = new ProgressDialog(getActivity());
            mDialog.setMessage("Mengupload... ");
            mDialog.show();

            String imageName = UUID.randomUUID().toString();
            final StorageReference imageFolder = storageReference.child("images/" + imageName);
            imageFolder.putFile(saveUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mDialog.dismiss();
                            Toast.makeText(getActivity(), "Terupload !!!", Toast.LENGTH_SHORT).show();
                            imageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //set value ke turnamen list jika gambar upload kita dapat mendownload link
                                    newTurnamen = new ModelJenisTurnamenMl();
                                    newTurnamen.setNamaTurnamen(addNamaTurnamen.getText().toString());
                                    newTurnamen.setDeskripsiTurnamen(addDeskripsiTurnamen.getText().toString());
                                    newTurnamen.setTanggalTurnamen(addTanggalTurnamen.getText().toString());
                                    newTurnamen.setHargaTurnamen(addHargaTurnamen.getText().toString());
                                    newTurnamen.setKontakTurnamen(addNomorWhatsApp.getText().toString());
                                    newTurnamen.setAuthorTurnamen(addAuthorTurnamen.getText().toString());
                                    newTurnamen.setInstagramTurnamen(addInstagramTurnamen.getText().toString());
                                    newTurnamen.setImageTurnamen(uri.toString());
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mDialog.dismiss();
                            Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mDialog.setMessage("Terupload !!! " + progress + "%");
                        }
                    });

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Common.PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            saveUri = data.getData();
            btnSelect.setText("Gambar Dipilih !");
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), Common.PICK_IMAGE_REQUEST);
    }

    private void loadData() {

        options = new FirebaseRecyclerOptions.Builder<ModelJenisTurnamenMl>().setQuery(DataRef, ModelJenisTurnamenMl.class).build();
        adapter = new FirebaseRecyclerAdapter<ModelJenisTurnamenMl, AddJenisTurnamenViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AddJenisTurnamenViewHolder holder, int position, @NonNull ModelJenisTurnamenMl model) {
                holder.tvJenisTurnamen.setText(model.getNamaTurnamen());
                Picasso.get().load(model.getImageTurnamen()).into(holder.ivJenisTurnamen);

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        Intent detailTurnamen = new Intent(getActivity(), UserListTurnamenDetail.class);
                        detailTurnamen.putExtra("listTurnamenKey", getRef(position).getKey());

                        startActivity(detailTurnamen);
                    }
                });

            }

            @NonNull
            @Override
            public AddJenisTurnamenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_turnamen_ml_item, parent, false);

                return new AddJenisTurnamenViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerViewJenisTurnamen.setAdapter(adapter);
    }


    private void showUpdateDialogTurnamen(final String key, final ModelJenisTurnamenMl item) {
        //alert dialog
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Ganti Menu Turnamen Disini");
        alertDialog.setMessage("Tolong isi semua kolom yang ada");

        LayoutInflater inflater = LayoutInflater.from(getContext());

        final View menu_add_turnamen = inflater.inflate(R.layout.menu_add_turnamen, null);

        addNamaTurnamen = menu_add_turnamen.findViewById(R.id.et_nama_turnamen);
        addDeskripsiTurnamen = menu_add_turnamen.findViewById(R.id.et_deskripsi_turnamen);
        addTanggalTurnamen = menu_add_turnamen.findViewById(R.id.et_tanggal_turnamen);
        addHargaTurnamen = menu_add_turnamen.findViewById(R.id.et_harga_turnamen);
        addNomorWhatsApp = menu_add_turnamen.findViewById(R.id.et_no_wa);
        addAuthorTurnamen = menu_add_turnamen.findViewById(R.id.et_author_turnamen);
        addInstagramTurnamen = menu_add_turnamen.findViewById(R.id.et_ig_turnamen);

        addNamaTurnamen.setText(item.getNamaTurnamen());
        addDeskripsiTurnamen.setText(item.getDeskripsiTurnamen());
        addTanggalTurnamen.setText(item.getTanggalTurnamen());
        addHargaTurnamen.setText(item.getHargaTurnamen());
        addNomorWhatsApp.setText(item.getKontakTurnamen());
        addAuthorTurnamen.setText(item.getAuthorTurnamen());
        addInstagramTurnamen.setText(item.getInstagramTurnamen());

        btnSelect = menu_add_turnamen.findViewById(R.id.btn_select);
        btnUpload = menu_add_turnamen.findViewById(R.id.btn_upload);
        btnSubmitData = menu_add_turnamen.findViewById(R.id.btn_submit_data);

        //buton iya

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(item);
            }
        });

        btnSubmitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setNamaTurnamen(addNamaTurnamen.getText().toString());
                item.setDeskripsiTurnamen(addDeskripsiTurnamen.getText().toString());
                item.setTanggalTurnamen(addTanggalTurnamen.getText().toString());
                item.setHargaTurnamen(addHargaTurnamen.getText().toString());
                item.setKontakTurnamen(addNomorWhatsApp.getText().toString());
                item.setAuthorTurnamen(addAuthorTurnamen.getText().toString());
                item.setInstagramTurnamen(addInstagramTurnamen.getText().toString());

                DataRef.child(key).setValue(item);

                Toast.makeText(getContext(), "Data Berhasil Ditambahkan !", Toast.LENGTH_SHORT)
                        .show();
            }
        })

        alertDialog.setView(menu_add_turnamen);
        alertDialog.setIcon(R.drawable.ic_add_black);
    

        alertDialog.show();
    }

    private void changeImage(final ModelJenisTurnamenMl item) {
        if (saveUri != null) {
            final ProgressDialog mDialog = new ProgressDialog(getContext());
            mDialog.setMessage("Mengupload... ");
            mDialog.show();

            String imageName = UUID.randomUUID().toString();
            final StorageReference imageFolder = storageReference.child("images/" + imageName);
            imageFolder.putFile(saveUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            mDialog.dismiss();
                            Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                            imageFolder.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //set value ke turnamen list jika gambar upload kita dapat mendownload link
                                    newTurnamen = new ModelJenisTurnamenMl();
                                    newTurnamen.setImageTurnamen(uri.toString());
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            mDialog.dismiss();
                            Toast.makeText(getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mDialog.setMessage("Terupload !!! " + progress + "%");
                        }
                    });

        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals(Common.UPDATE)) {
            showUpdateDialogTurnamen(adapter.getRef(item.getOrder()).getKey(), adapter.getItem(item.getOrder()));
        } else if (item.getTitle().equals(Common.DELETE)) {

        }
        return super.onContextItemSelected(item);
    }
}