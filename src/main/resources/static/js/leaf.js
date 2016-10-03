$(document).ready(function() {
  $('#togglingForm')
    .bootstrapValidator({
      message: 'This value is not valid',
      feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
      },
      submitHandler: function(validator, form, submitButton) {
        // Do nothing
      },
      fields: {
        firstname: {
          validators: {
            notEmpty: {
              message: 'The first name is required'
            }
          }
        },
        lastname: {
          validators: {
            notEmpty: {
              message: 'The last name is required'
            }
          }
        }
      }
    })
    .find('button[data-toggle]')
    .on('click', function() {
      var $target = $($(this).attr('data-toggle'));
      // Show or hide the additional fields
      // They will or will not be validated based on their visibilities
      $target.toggle();
      if (!$target.is(':visible')) {
        // Enable the submit buttons in case additional fields are not valid
        $('#togglingForm').data('bootstrapValidator').disableSubmitButtons(false);
      }
    });
});